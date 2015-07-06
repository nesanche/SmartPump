package com.smartpump.bismara.bismaraapp.entities;

import com.google.gson.annotations.Expose;



/**
 * Clase que representa la entidad de un usuario registrado en el sistema. Sea
 * cual sea su rol.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class User {

    /** Nombre de usuario. */
    @Expose
    private String username;
    /** Constraseña */
    @Expose
    private String password;
    /** Estado del usuario */
    @Expose
    private UserState state;

    /**
     * Devuelve el username del usuario.
     * 
     * @return el username del usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el username del usuario.
     * 
     * @param username
     *            el username del usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Devuelve la constraseña del usuario.
     * 
     * @return la constraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password
     *            la contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Devuelve el estado actual del usuario.
     * 
     * @return el estado actual del usuario.
     */
    public UserState getState() {
        return state;
    }

    /**
     * Establece el estado actual del usuario.
     * 
     * @param state
     *            el estado actual del usuario.
     */
    public void setState(UserState state) {
        this.state = state;
    }

}
