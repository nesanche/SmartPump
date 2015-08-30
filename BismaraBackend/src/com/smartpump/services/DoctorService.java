package com.smartpump.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartpump.dao.interfaces.IDoctorDao;
import com.smartpump.dao.interfaces.IUserDao;
import com.smartpump.model.Doctor;
import com.smartpump.model.Patient;
import com.smartpump.model.VerificationToken;
import com.smartpump.model.notifications.Notification;
import com.smartpump.notifications.NotificationThread;
import com.smartpump.notifications.NotificationTypeConstants;
import com.smartpump.notifications.NotificationXMLParser;
import com.smartpump.services.interfaces.IMailService;
import com.smartpump.utils.ApplicationConstants;
import com.smartpump.utils.TokenGenerator;

/**
 * Servicio que representa la administración del registro, los datos y las
 * confirmaciones de los doctores en el sistema.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class DoctorService {

    /** URL de confirmación para el registro del doctor. */
    private static final String CONFIRM_URL = ApplicationConstants.REST_URL
            + "/rest/doctors/confirm";
    /** Generador de token. */
    @Autowired
    private TokenGenerator tokenGenerator;
    /** Entidad responsable del manejo de persistencia de los doctores. */
    @Autowired
    private IDoctorDao doctorDao;
    /** Entidad responsable del manejo de persistencia de los usuarios. */
    @Autowired
    private IUserDao userDao;
    /** Servicio de envío de mails. */
    @Autowired
    private IMailService mailService;
    /**
     * Entidad encargada de construir la notificación recorriendo un archivo xml
     * de configuración.
     */
    @Autowired
    private NotificationXMLParser notificationBuilder;

    /**
     * Establece el generador de tokens.
     * 
     * @param tokenGenerator
     *            el generador de tokens.
     */
    public void setTokenGenerator(TokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
    }

    /**
     * Establece el controlador DAO para doctor.
     * 
     * @param doctorDao
     *            el controlador DAO.
     */
    public void setDoctorDao(IDoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    /**
     * Establece el servicio de mail responsable del envío.
     * 
     * @param mailService
     *            el servicio de mail.
     */
    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    /**
     * Método responsable del registro o actualización de un doctor en el
     * sistema.
     * 
     * @param doctor
     *            el doctor a persistir/actualizar.
     * @return el doctor con los datos actualizados.
     */
    public Doctor registerDoctor(Doctor doctor) {
        Doctor result = doctorDao.registerDoctor(doctor);
        VerificationToken token = userDao.registerToken(doctor.getUser(),
                tokenGenerator.generateToken());
        sendToVerifyMail(result, token);
        return result;
    }

    /**
     * Método responsable de devolver un doctor en función de su nombre de
     * usuario y contraseña.
     * 
     * @param username
     *            el nombre de usuario del doctor.
     * @param password
     *            la contraseña del doctor.
     * @return el doctor asociado a esos datos, null en caso contrario.
     */
    public Doctor getDoctor(String username, String password) {
        Doctor doctor = doctorDao.getDoctor(username, password);
        return doctor;
    }

    /**
     * Método responsable de devolver un doctor en función de su matrícula
     * 
     * @param registrationNumber
     *            la matrícula del doctor.
     * @return el doctor asociado, null en caso contrario.
     */
    public Doctor getDoctor(String registrationNumber) {
        Doctor doctor = doctorDao
                .getDoctorByRegistrationNumber(registrationNumber);
        return doctor;
    }

    /**
     * Método responsable de confirmar a un doctor para que su estado pase a ser
     * registrado en el sistema. Realiza el envío del mail al usuario para
     * avisarle de esta información.
     * 
     * @param id
     *            el id del usuario del doctor.
     * @param token
     *            el contenido del token de confirmación.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean confirmDoctor(int id, String token) {
        Doctor doctor = doctorDao.getDoctorByUserId(id);
        if (doctor == null) {
            throw new RuntimeException(
                    "No existe un doctor con ese id de usuario.");
        }
        boolean result = userDao.confirmUser(id, token);
        if (result) {
            sendConfirmationNotification(doctor);
            sendConfirmationMail(doctor);

        }
        return result;
    }

    /**
     * Método responsable de verificar la existencia de un doctor asociado a un
     * mail determinado.
     * 
     * @param email
     *            el mail a comparar.
     * @return true si existe un doctor con ese mail, false en caso contrario.
     */
    public boolean verifyEmail(String email) {
        return doctorDao.verifyEmail(email);
    }

    /**
     * Método responsable de obtener los pacientes asociados a un doctor.
     * 
     * @param doctorId
     *            el id del doctor.
     * @return una lista con los pacientes asociados al doctor.
     */
    public List<Patient> getPatientsOfDoctor(int doctorId) {
        return doctorDao.getPatientsOfDoctor(doctorId);
    }

    /**
     * Método que envia el mail a la cuenta de administración para que valide al
     * doctor.
     * 
     * @param doctor
     *            el doctor a validar.
     * @param token
     *            el token de confirmación del mismo.
     */
    private void sendToVerifyMail(Doctor doctor, VerificationToken token) {
        mailService.sendMail(ApplicationConstants.BROKER_MAIL,
                ApplicationConstants.ADMINISTRATION_MAIL, "Confirma",
                getToVerifyMailBody(doctor, token));
    }

    /**
     * Método que compone el mail que se envía a administración.
     * 
     * @param doctor
     *            el doctor a confirmar.
     * @param token
     *            el token de verificación asociado.
     * @return el cuerpo del mail.
     */
    private String getToVerifyMailBody(Doctor doctor, VerificationToken token) {
        StringBuilder builder = new StringBuilder(
                "Hemos recibido un nuevo registro de doctor. Sus datos son:\n");
        builder.append("Apellido y nombre: " + doctor.getLastName() + ", "
                + doctor.getFirstName() + "\n");
        builder.append("Matrícula: " + doctor.getRegistrationNumber() + "\n");
        builder.append("Email: " + doctor.getEmail() + "\n");
        builder.append("Teléfono: " + doctor.getPhone() + "\n");
        builder.append("Nombre de usuario: " + doctor.getUser().getUsername()
                + "\n");
        builder.append("Para confirmar su registración por favor hacer click aquí:"
                + "\n");
        builder.append(CONFIRM_URL + "?id=" + doctor.getUser().getId()
                + "&token=" + token.getToken() + "\n\n");
        builder.append("Muchas gracias,\n");
        builder.append("Bismara system");
        return builder.toString();
    }

    /**
     * Método que envía el mail al doctor avisando que su cuenta ha sido
     * habilitada.
     * 
     * @param doctor
     *            el doctor a avisar.
     */
    private void sendConfirmationMail(Doctor doctor) {
        mailService
                .sendMail(
                        ApplicationConstants.BROKER_MAIL,
                        doctor.getEmail(),
                        "Tu cuenta ha sido activada",
                        "¡Felicitaciones! Tu cuenta ha sido activada. Ya puedes comenzar a utilizar los servicios de Bismara.");
    }

    /**
     * Método responsable de enviar la notificación al paciente.
     * 
     * @param idPump
     *            el id de la bomba asociada al paciente.
     */
    private void sendConfirmationNotification(Doctor doctor) {
        Notification notification = notificationBuilder.buildNotification(
                NotificationTypeConstants.TYPE_CONFIRMED_ACCOUNT,
                doctor.getUser());
        NotificationThread notificationThread = new NotificationThread(
                notification);
        Thread thread = new Thread(notificationThread);
        thread.start();
    }

}
