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
     * Consulta de doctores que devuelve uno filtrando por email, �nico
     * par�metro.
     */
    public static final String DOCTOR_VERIFY_EMAIL_QUERY = "Doctor.verifyEmail";
    /**
     * Consulta de doctores que devuelve uno filtrando por el id de su usuario.
     * �nico par�metro "userid".
     */
    public static final String DOCTOR_GET_BY_USER_ID = "Doctor.getByUserId";

    // -----------------------------Usuarios-------------------------------
    /** Consulta de usuarios que devuelve todos los registros almacenados. */
    public static final String USER_GET_ALL_QUERY = "User.getAll";
    /**
     * Consulta de usuarios que devuelve uno filtrando por username, �nico
     * par�metro.
     */
    public static final String USER_GET_BY_USERNAME_QUERY = "User.getByUsername";
    // -----------------------------Tokens de
    // verificaci�n-------------------------------
    /**
     * Consulta de token de verificaci�n que devuelve un token filtrando por id
     * de usuario y por token, ambos par�metros.
     */
    public static final String VERIFICATION_TOKEN_CONFIRM_USER_QUERY = "VerificationToken.confirmUser";

}
