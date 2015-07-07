package com.smartpump.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.smartpump.dao.constants.Tables;

/**
 * Entidad que representa el rol que cumple el usuario dentro del sistema.
 * Generado por asuntos de seguridad. Los roles por defecto son los siguientes:
 * 1- Administrator 2- Doctor 3- Patient 4- Researcher 5- Insurance_employee
 * 
 * @author Franco Ariel Salonia
 *
 */
@Entity
@Table(name = Tables.USER_ROLE_TABLE)
@XmlRootElement
public class UserRole {

    /** Id autogenerado. */
    @Id
    @GeneratedValue
    private int id;
    /** Descripción. */
    private String description;

    /**
     * Constructor por defecto
     */
    public UserRole() {
    }

    /**
     * Constructor con atributos.
     * 
     * @param id
     *            el id del rol de usuario.
     * @param description
     *            la descripción del rol de usuario.
     */
    public UserRole(int id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * Devuelve el id del rol de usuario.
     * 
     * @return el id del rol de usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id del rol de usuario.
     * 
     * @param id
     *            el id del rol de usuario.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve la descripción del rol de usuario.
     * 
     * @return la descripción del rol de usuario.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del rol de usuario.
     * 
     * @param description
     *            la descripción del rol de usuario.
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
