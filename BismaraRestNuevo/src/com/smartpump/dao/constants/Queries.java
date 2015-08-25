package com.smartpump.dao.constants;

/**
 * Clase que encapsula todas las constantes referidas a las consultas. Utilizado
 * por las entidades y los brokers DAO.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class Queries {

    // -----------------------------Doctores-------------------------------
    /** Consulta de doctores que devuelve todos los registros almacenados. */
    public static final String DOCTOR_GET_ALL_QUERY = "Doctor.getAll";
    /**
     * Consulta de doctores que devuelve uno filtrando por username y password.
     * Ambos par�metros.
     */
    public static final String DOCTOR_GET_BY_USERNAME_AND_PASSWORD_QUERY = "Doctor.getByUsernameAndPassword";
    /**
     * Consulta de doctores que devuelve uno filtrando por username y password.
     * Ambos par�metros.
     */
    public static final String DOCTOR_GET_BY_REGISTRATION_NUMBER = "Doctor.getByRegistrationNumber";
    /**
     * Consulta de doctores que devuelve uno filtrando por email, �nico
     * par�metro.
     */
    public static final String DOCTOR_VERIFY_EMAIL_QUERY = "Doctor.verifyEmail";
    /**
     * Consulta de doctores que devuelve uno filtrando por el id de su usuario.
     * �nico par�metro "userid".
     */
    public static final String DOCTOR_GET_BY_USER_ID = "Doctor.getByUserId";

    // -----------------------------GCM Registro----------------------------
    /**
     * Consulta de registros GCM que devuelve uno filtrando por el id de su
     * usuario. �nico par�metro "userid".
     */
    public static final String GCM_REGISTRATION_GET_BY_USER_ID = "GCMRegistration.getByUserId";

    // -----------------------------Notificaciones---------------------------

    /**
     * Consulta de notificaciones que devuelve uno filtrando por el id de su
     * usuario. �nico par�metro "userid".
     */
    public static final String NOTIFICATION_GET_BY_USER_ID = "Notification.getByUserId";

    // -----------------------------Pacientes-------------------------------
    /**
     * Consulta de pacientes que devuelve uno filtrando por email, �nico
     * par�metro.
     */
    public static final String PATIENT_VERIFY_EMAIL_QUERY = "Patient.verifyEmail";
    /**
     * Consulta de pacientes que devuelve uno filtrando por id de bomba.
     */
    public static final String PATIENT_GET_BY_PUMP_ID = "Patient.getByPumpId";
    /**
     * Consulta de pacientes que devuelve uno filtrando por id de usuario.
     */
    public static final String PATIENT_GET_BY_USER_ID = "Patient.getByUserId";

    /**
     * Consulta de pacientes que devuelve todos los que pertenecen a un doctor,
     * su id se env�a por par�metro.
     */
    public static final String PATIENT_GET_PATIENTS_LIST_OF_DOCTOR = "Patient.getListOfDoctor";

    /**
     * Obtiene las programaciones de un paciente filtrando por su id. Par�metro
     * idPatient.
     */
    public static final String PATIENT_GET_SCHEDULES = "Patient.getSchedules";

    /**
     * Obtiene las dosis de una programaci�n filtrando por su id. Par�metro
     * idSchedule.
     */
    public static final String PATIENT_GET_DOSES_OF_SCHEDULE = "Patient.getScheduleDoses";

    // -----------------------------Bombas-------------------------------
    /** Consulta de bombas que devuelve una bomba filtrando por direcci�n MAC. */
    public static final String PUMP_GET_BY_MAC_ADDRESS = "Pump.getByMacAddress";

    // -----------------------------Usuarios-------------------------------
    /** Consulta de usuarios que devuelve todos los registros almacenados. */
    public static final String USER_GET_ALL_QUERY = "User.getAll";
    /**
     * Consulta de usuarios que devuelve uno filtrando por username, �nico
     * par�metro.
     */
    public static final String USER_GET_BY_USERNAME_QUERY = "User.getByUsername";

    /**
     * Consulta de usuarios que devuelve uno filtrando por username y password,
     * ambos par�metros.
     */
    public static final String USER_GET_BY_USERNAME_AND_PASSWORD_QUERY = "User.getByUsernameAndPassword";
    // -----------------------------Tokens de
    // verificaci�n-------------------------------
    /**
     * Consulta de token de verificaci�n que devuelve un token filtrando por id
     * de usuario y por token, ambos par�metros.
     */
    public static final String VERIFICATION_TOKEN_CONFIRM_USER_QUERY = "VerificationToken.confirmUser";

}
