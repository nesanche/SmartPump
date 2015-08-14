package com.smartpump.bismara.requestmanager.model;


/**
 * Representa un registro GCM para poder utilizar los servicios de mensajería.
 * Los mismos son provistos cuando un usuario comienza a utilizar un dispositivo
 * android.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class GCMRegistration {

    /** El id del registro GCM. */
    private int id;
    /** El RegId obtenido por GCM. */
    private String registrationId;
    /** El usuario asociado al registro. */
    private User user;

    /**
     * Devuelve el id del registro GCM.
     * 
     * @return el id del registro GCM.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id del registro GCM.
     * 
     * @param id
     *            el id del registro GCM.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el RegId obtenido por GCM.
     * 
     * @return el RegId obtenido por GCM.
     */
    public String getRegistrationId() {
        return registrationId;
    }

    /**
     * Establece el RegId obtenido por GCM.
     * 
     * @param registrationId
     *            el RegId obtenido por GCM.
     */
    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    /**
     * Devuelve el usuario asociado al registro.
     * 
     * @return el usuario asociado al registro.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario asociado al registro.
     * 
     * @param user
     *            el usuario asociado al registro.
     */
    public void setUser(User user) {
        this.user = user;
    }

}
