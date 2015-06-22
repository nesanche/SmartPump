package com.smartpump.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartpump.dao.interfaces.IDoctorDao;
import com.smartpump.model.Doctor;
import com.smartpump.model.VerificationToken;
import com.smartpump.services.interfaces.IMailService;
import com.smartpump.utils.TokenGenerator;

/**
 * Servicio que representa la administraci�n del registro, los datos y las
 * confirmaciones de los doctores en el sistema.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class DoctorService {

    /** Direcci�n de mail utilizada para el env�o de los mismos. */
    // private static final String FROM_EMAIL = "no.reply.bismara@gmail.com";
    private static final String FROM_EMAIL = "some.raiseyourconfidence@gmail.com";
    /** Direcci�n de mail destino para enviar la confirmaci�n del doctor. */
    private static final String TO_MAIL = "bismara.staff@gmail.com";
    /** URL de confirmaci�n para el registro del doctor. */
    private static final String CONFIRM_URL = "http://bismara.elasticbeanstalk.com/rest/doctors/confirm";
    /** Generador de token. */
    @Autowired
    private TokenGenerator tokenGenerator;
    /** Entidad responsable del manejo de persistencia de los doctores. */
    @Autowired
    private IDoctorDao doctorDao;
    /** Servicio de env�o de mails. */
    @Autowired
    private IMailService mailService;

    /**
     * M�todo responsable del registro o actualizaci�n de un doctor en el
     * sistema.
     * 
     * @param doctor
     *            el doctor a persistir/actualizar.
     * @return el doctor con los datos actualizados.
     */
    public Doctor registerDoctor(Doctor doctor) {
        Doctor result = doctorDao.registerDoctor(doctor);
        VerificationToken token = doctorDao.registerToken(doctor,
                tokenGenerator.generateToken());
        sendToVerifyMail(result, token);
        return result;
    }

    /**
     * M�todo responsable de devolver un doctor en funci�n de su nombre de
     * usuario y contrase�a.
     * 
     * @param username
     *            el nombre de usuario del doctor.
     * @param password
     *            la contrase�a del doctor.
     * @return el doctor asociado a esos datos, null en caso contrario.
     */
    public Doctor getDoctor(String username, String password) {
        Doctor doctor = doctorDao.getDoctor(username, password);
        return doctor;
    }

    /**
     * M�todo responsable de confirmar a un doctor para que su estado pase a ser
     * registrado en el sistema. Realiza el env�o del mail al usuario para
     * avisarle de esta informaci�n.
     * 
     * @param id
     *            el id del usuario del doctor.
     * @param token
     *            el contenido del token de confirmaci�n.
     * @return true si la operaci�n fue exitosa, false en caso contrario.
     */
    public boolean confirmDoctor(String id, String token) {
        boolean result = doctorDao.confirmDoctor(id, token);
        if (result) {
            Doctor doctor = doctorDao.getDoctorByUserId(Integer.parseInt(id));
            sendConfirmationMail(doctor);
        }
        return result;
    }

    /**
     * M�todo responsable de verificar la existencia de un doctor asociado a un
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
     * M�todo que envia el mail a la cuenta de administraci�n para que valide al
     * doctor.
     * 
     * @param doctor
     *            el doctor a validar.
     * @param token
     *            el token de confirmaci�n del mismo.
     */
    private void sendToVerifyMail(Doctor doctor, VerificationToken token) {
        mailService.sendMail(FROM_EMAIL, TO_MAIL, "Confirma",
                getToVerifyMailBody(doctor, token));
    }

    /**
     * M�todo que compone el mail que se env�a a administraci�n.
     * 
     * @param doctor
     *            el doctor a confirmar.
     * @param token
     *            el token de verificaci�n asociado.
     * @return el cuerpo del mail.
     */
    private String getToVerifyMailBody(Doctor doctor, VerificationToken token) {
        StringBuilder builder = new StringBuilder(
                "Hemos recibido un nuevo registro de doctor. Sus datos son:\n");
        builder.append("Apellido y nombre: " + doctor.getLastName() + ", "
                + doctor.getFirstName() + "\n");
        builder.append("Matr�cula: " + doctor.getRegistrationNumber() + "\n");
        builder.append("Email: " + doctor.getEmail() + "\n");
        builder.append("Tel�fono: " + doctor.getPhone() + "\n");
        builder.append("Nombre de usuario: " + doctor.getUser().getUsername()
                + "\n");
        builder.append("Para confirmar su registraci�n por favor hacer click aqu�:"
                + "\n");
        builder.append(CONFIRM_URL + "?id=" + doctor.getUser().getId()
                + "&token=" + token.getToken() + "\n\n");
        builder.append("Muchas gracias,\n");
        builder.append("Bismara system");
        return builder.toString();
    }

    /**
     * M�todo que env�a el mail al doctor avisando que su cuenta ha sido
     * habilitada.
     * 
     * @param doctor
     *            el doctor a avisar.
     */
    private void sendConfirmationMail(Doctor doctor) {
        mailService
                .sendMail(
                        FROM_EMAIL,
                        doctor.getEmail(),
                        "Tu cuenta ha sido activada",
                        "�Felicitaciones! Tu cuenta ha sido activada. Ya puedes comenzar a utilizar los servicios de Bismara.");
    }

}
