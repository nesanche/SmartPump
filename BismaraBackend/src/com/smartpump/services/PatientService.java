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
 * Servicio que representa la administraci�n del registro, los datos y las
 * confirmaciones de los pacientes en el sistema.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class PatientService {

    /** URL de confirmaci�n para el registro del doctor. */
    private static final String CONFIRM_URL = ApplicationConstants.REST_URL
            + "/rest/patients/confirm";
    /** Entidad responsable del manejo de persistencia de los pacientes. */
    @Autowired
    private IPatientDao patientDao;
    /** Entidad responsable del manejo de persistencia de los usuarios. */
    @Autowired
    private IUserDao userDao;
    /** Servicio de env�o de mails. */
    @Autowired
    private IMailService mailService;
    /** Generador de token. */
    @Autowired
    private TokenGenerator tokenGenerator;

    /**
     * M�todo responsable de verificar la existencia de un paciente asociado a
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
     * M�todo responsable de verificar una bomba determinada, enviando su
     * direcci�n de MAC y su pin de validaci�n.
     * 
     * @param macAddress
     *            la direcci�n MAC de la bomba.
     * @param verificationPin
     *            el pin de validaci�n de la bomba.
     * @return true si existe una bomba con esa MAC y ese pin, false en caso
     *         contrario.
     */
    public boolean verifyPump(String macAddress, int verificationPin) {
        return patientDao.verifyPump(macAddress, verificationPin) != null;
    }

    /**
     * M�todo responsable de verificar una bomba determinada, enviando su
     * direcci�n de MAC y su pin de validaci�n.
     * 
     * @param macAddress
     *            la direcci�n MAC de la bomba.
     * @param verificationPin
     *            el pin de validaci�n de la bomba.
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
     * M�todo que se encarga de confirmar la cuenta de un paciente, cambiando a
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
     * M�todo que envia el mail a la cuenta del m�dico del paciente para que
     * valide al paciente.
     * 
     * @param patient
     *            el paciente a validar.
     * @param token
     *            el token de confirmaci�n del mismo.
     */
    private void sendToVerifyMail(Patient patient, VerificationToken token) {
        mailService.sendMail(ApplicationConstants.BROKER_MAIL, patient
                .getDoctor().getEmail(), "Confirma",
                getToVerifyMailBody(patient, token));
    }

    /**
     * M�todo que compone el mail que se env�a al m�dico del paciente.
     * 
     * @param doctor
     *            el doctor a confirmar.
     * @param token
     *            el token de verificaci�n asociado.
     * @return el cuerpo del mail.
     */
    private String getToVerifyMailBody(Patient patient, VerificationToken token) {
        StringBuilder builder = new StringBuilder(
                "Hemos recibido un nuevo registro de un paciente. Sus datos son:\n");
        builder.append("Apellido y nombre: " + patient.getLastName() + ", "
                + patient.getFirstName() + "\n");
        builder.append("Direcci�n donde vive: " + patient.getAddress() + "\n");
        builder.append("Email: " + patient.getEmail() + "\n");
        builder.append("Tel�fono 1: " + patient.getPhoneOne() + "\n");
        builder.append("Tel�fono 2: " + patient.getPhoneTwo() + "\n");
        builder.append("Nombre de usuario: " + patient.getUser().getUsername()
                + "\n");
        builder.append("Para confirmar su registraci�n por favor hacer click aqu�:"
                + "\n");
        builder.append(CONFIRM_URL + "?id=" + patient.getUser().getId()
                + "&token=" + token.getToken() + "\n\n");
        builder.append("Muchas gracias,\n");
        builder.append("Bismara system");
        return builder.toString();
    }

    /**
     * M�todo que env�a el mail al doctor paciente que su cuenta ha sido
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
                        "�Felicitaciones! Tu cuenta ha sido activada. Ya puedes comenzar a utilizar los servicios de Bismara.");
    }

}
