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
     * Ambos parámetros.
     */
    public static final String DOCTOR_GET_BY_USERNAME_AND_PASSWORD_QUERY = "Doctor.getByUsernameAndPassword";
    /**
     * Consulta de doctores que devuelve uno filtrando por email, único
     * parámetro.
     */
    public static final String DOCTOR_VERIFY_EMAIL_QUERY = "Doctor.verifyEmail";
    /**
     * Consulta de doctores que devuelve uno filtrando por el id de su usuario.
     * Único parámetro "userid".
     */
    public static final String DOCTOR_GET_BY_USER_ID = "Doctor.getByUserId";

    // -----------------------------Usuarios-------------------------------
    /** Consulta de usuarios que devuelve todos los registros almacenados. */
    public static final String USER_GET_ALL_QUERY = "User.getAll";
    /**
     * Consulta de usuarios que devuelve uno filtrando por username, único
     * parámetro.
     */
    public static final String USER_GET_BY_USERNAME_QUERY = "User.getByUsername";
    // -----------------------------Tokens de
    // verificación-------------------------------
    /**
     * Consulta de token de verificación que devuelve un token filtrando por id
     * de usuario y por token, ambos parámetros.
     */
    public static final String VERIFICATION_TOKEN_CONFIRM_USER_QUERY = "VerificationToken.confirmUser";

}
