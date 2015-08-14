package com.smartpump.bismara.requestmanager.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase que representa un token de verificación para que el usuario pueda ser
 * confirmado y comenzar a utilizar el sistema.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class VerificationToken {

    /** Tiempo establecido para el vencimiento del token. */
    private static final int EXPIRATION = 60 * 24 * 7;

    /** Id autogenerado. */
    private int id;

    /** Contenido del token. */
    private String token;

    /** Usuario al que pertenece el token. */
    private User user;
    /** Fecha de vencimiento del token. */
    private Date expirationDate;

    /**
     * Constructor por defecto.
     */
    public VerificationToken() {
    }

    /**
     * Constructor con parámetros. Establece el token y su fecha de vencimiento.
     * 
     * @param token
     *            el contenido del token.
     * @param user
     *            el usuario vinculado.
     */
    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expirationDate = calculateExpirationDate(EXPIRATION);
    }

    /**
     * Método que calcula la fecha de vencimiento de acuerdo a la creación y al
     * tiempo determinado para que expire.
     * 
     * @param expirationTimeInMinutes
     *            tiempo en minutos que demora en vencer el token.
     * @return la fecha de expiración.
     */
    private Date calculateExpirationDate(int expirationTimeInMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, expirationTimeInMinutes);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * Devuelve el id del token.
     * 
     * @return el id del token.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id del token.
     * 
     * @param id
     *            el id del token.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el contenido del token.
     * 
     * @return el contenido del token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Establece el contenido del token.
     * 
     * @param token
     *            el contenido del token.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Devuelve el Usuario relacionado al token.
     * 
     * @return el usuario relacionado al token.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario relacionado al token.
     * 
     * @param user
     *            el usuario relacionado al token.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Devuelve la fecha de vencimiento del token.
     * 
     * @return la fecha de vencimiento del token.
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Establece la fecha de vencimiento del token.
     * 
     * @param expirationDate
     *            la fecha de vencimiento del token.
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

}
