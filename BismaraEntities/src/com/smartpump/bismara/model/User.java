package com.smartpump.bismara.model;


/**
 * Clase que representa la entidad de un usuario registrado en el sistema. Sea
 * cual sea su rol.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class User {

    /** Id autogenerado del usuario. */
    private int id;
    /** Nombre de usuario. */
    private String username;
    /** Constraseña */
    private String password;
    /** Estado del usuario */
    private UserState state;
    /** Rol del usuario */
    private UserRole role;

    /**
     * Devuelve el id del usuario
     * 
     * @return el idi del usuario
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id del usuario.
     * 
     * @param id
     *            el id del usuario.
     */
    public void setId(int id) {
        this.id = id;
    }

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

    /**
     * Devuelve el rol del usuario.
     * 
     * @return el rol del usuario.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Establece el rol del usuario.
     * 
     * @param role
     *            el rol del usuario.
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Devuelve una bandera que indica que el usuario puede realizar operaciones
     * o no.
     * 
     * @return una bandera que indica si el usuario puede realizar operaciones o
     *         no.
     */
    public boolean isEnabled() {
        return this.state.getId() == 2;
    }

}
