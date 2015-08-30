package com.smartpump.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartpump.dao.interfaces.IPatientDao;
import com.smartpump.dao.interfaces.IUserDao;
import com.smartpump.model.Patient;
import com.smartpump.model.VerificationToken;
import com.smartpump.model.scheduling.Pump;
import com.smartpump.services.interfaces.IMailService;
import com.smartpump.utils.ApplicationConstants;
import com.smartpump.utils.TokenGenerator;

/**
 * Servicio que representa la administración del registro, los datos y las
 * confirmaciones de los pacientes en el sistema.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class PatientService {

    /** URL de confirmación para el registro del doctor. */
    private static final String CONFIRM_URL = ApplicationConstants.REST_URL
            + "/rest/patients/confirm";
    /** Entidad responsable del manejo de persistencia de los pacientes. */
    @Autowired
    private IPatientDao patientDao;
    /** Entidad responsable del manejo de persistencia de los usuarios. */
    @Autowired
    private IUserDao userDao;
    /** Servicio de envío de mails. */
    @Autowired
    private IMailService mailService;
    /** Generador de token. */
    @Autowired
    private TokenGenerator tokenGenerator;

    /**
     * Método responsable de verificar la existencia de un paciente asociado a
     * un mail determinado.
     * 
     * @param email
     *            el mail a comparar.
     * @return true si existe un paciente con ese mail, false en caso contrario.
     */
    public boolean verifyEmail(String email) {
        return patientDao.verifyEmail(email);
    }

    /**
     * Método responsable de verificar una bomba determinada, enviando su
     * dirección de MAC y su pin de validación.
     * 
     * @param macAddress
     *            la dirección MAC de la bomba.
     * @param verificationPin
     *            el pin de validación de la bomba.
     * @return true si existe una bomba con esa MAC y ese pin, false en caso
     *         contrario.
     */
    public boolean verifyPump(String macAddress, int verificationPin) {
        return patientDao.verifyPump(macAddress, verificationPin) != null;
    }

    /**
     * Método responsable de verificar una bomba determinada, enviando su
     * dirección de MAC y su pin de validación.
     * 
     * @param macAddress
     *            la dirección MAC de la bomba.
     * @param verificationPin
     *            el pin de validación de la bomba.
     * @return true si existe una bomba con esa MAC y ese pin, false en caso
     *         contrario.
     */
    public Pump getPump(String macAddress, int verificationPin) {
        return patientDao.verifyPump(macAddress, verificationPin);
    }

    /**
     * Crea o actualiza un paciente en el sistema. Hace el manejo de emails.
     * 
     * @param patient
     * @return
     */
    public Patient registerPatient(Patient patient) {
        Patient result = patientDao.registerPatient(patient);
        VerificationToken token = userDao.registerToken(patient.getUser(),
                tokenGenerator.generateToken());
        sendToVerifyMail(result, token);
        return result;
    }

    /**
     * Método que se encarga de confirmar la cuenta de un paciente, cambiando a
     * "Registered" su estado.
     * 
     * @param id
     *            el id de usuario del paciente.
     * @param token
     *            el token asociado a ese id.
     * @return true si todo fue exitoso, false en caso contrario.
     */
    public boolean confirmPatient(int id, String token) {
        Patient patient = patientDao.getPatientByUserId(id);
        if (patient == null) {
            throw new RuntimeException(
                    "No existe un paciente con ese id de usuario.");
        }
        boolean result = userDao.confirmUser(id, token);
        if (result) {
            sendConfirmationMail(patient);
        }
        return result;
    }

    /**
     * Método que envia el mail a la cuenta del médico del paciente para que
     * valide al paciente.
     * 
     * @param patient
     *            el paciente a validar.
     * @param token
     *            el token de confirmación del mismo.
     */
    private void sendToVerifyMail(Patient patient, VerificationToken token) {
        mailService.sendMail(ApplicationConstants.BROKER_MAIL, patient
                .getDoctor().getEmail(), "Confirma",
                getToVerifyMailBody(patient, token));
    }

    /**
     * Método que compone el mail que se envía al médico del paciente.
     * 
     * @param doctor
     *            el doctor a confirmar.
     * @param token
     *            el token de verificación asociado.
     * @return el cuerpo del mail.
     */
    private String getToVerifyMailBody(Patient patient, VerificationToken token) {
        StringBuilder builder = new StringBuilder(
                "Hemos recibido un nuevo registro de un paciente. Sus datos son:\n");
        builder.append("Apellido y nombre: " + patient.getLastName() + ", "
                + patient.getFirstName() + "\n");
        builder.append("Dirección donde vive: " + patient.getAddress() + "\n");
        builder.append("Email: " + patient.getEmail() + "\n");
        builder.append("Teléfono 1: " + patient.getPhoneOne() + "\n");
        builder.append("Teléfono 2: " + patient.getPhoneTwo() + "\n");
        builder.append("Nombre de usuario: " + patient.getUser().getUsername()
                + "\n");
        builder.append("Para confirmar su registración por favor hacer click aquí:"
                + "\n");
        builder.append(CONFIRM_URL + "?id=" + patient.getUser().getId()
                + "&token=" + token.getToken() + "\n\n");
        builder.append("Muchas gracias,\n");
        builder.append("Bismara system");
        return builder.toString();
    }

    /**
     * Método que envía el mail al doctor paciente que su cuenta ha sido
     * habilitada.
     * 
     * @param patient
     *            el paciente a avisar.
     */
    private void sendConfirmationMail(Patient patient) {
        mailService
                .sendMail(
                        ApplicationConstants.BROKER_MAIL,
                        patient.getEmail(),
                        "Tu cuenta ha sido activada",
                        "¡Felicitaciones! Tu cuenta ha sido activada. Ya puedes comenzar a utilizar los servicios de Bismara.");
    }

}
