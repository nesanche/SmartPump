package com.smartpump.bismara.requestmanager.model;


/**
 * Clase que representa el estado actual del usuario en el sistema. Por defecto
 * se van a suponer 3 estados: 1. Pending: El usuario est� registrado pero
 * esperando aprobaci�n para utilizar Bismara. 2. Registered: El usuario est�
 * registrado y autorizado para utilizar Bismara. 3. Deleted: El usuario fue
 * removido del sistema. No puede utilizar Bismara.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class UserState {

    /** Id auto generado. */
    private int id;
    /** Descripci�n. */
    private String description;

    /**
     * Constructor por defecto
     */
    public UserState() {
    }

    /**
     * Constructor con atributos.
     * 
     * @param id
     *            el id del estado de usuario.
     * @param description
     *            la descripci�n del estado de usuario.
     */
    public UserState(int id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * Devuelve el id del estado de usuario.
     * 
     * @return el id del estado de usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id del estado de usuario.
     * 
     * @param id
     *            el id del estado de usuario.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve la descripci�n del estado de usuario.
     * 
     * @return la descripci�n del estado de usuario.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripci�n del estado de usuario.
     * 
     * @param description
     *            la descripci�n del estado de usuario.
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
