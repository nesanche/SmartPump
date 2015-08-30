package com.smartpump.model.notifications;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.smartpump.dao.constants.Queries;
import com.smartpump.dao.constants.Tables;
import com.smartpump.model.User;

/**
 * Representa un registro GCM para poder utilizar los servicios de mensajería.
 * Los mismos son provistos cuando un usuario comienza a utilizar un dispositivo
 * android.
 * 
 * @author Franco Ariel Salonia
 *
 */
@Entity
@NamedQueries(@NamedQuery(name = Queries.GCM_REGISTRATION_GET_BY_USER_ID, query = "SELECT g FROM GCMRegistration g WHERE g.user.id=:userid"))
@Table(name = Tables.GCM_REGISTRATION_TABLE)
@XmlRootElement
public class GCMRegistration {

    /** El id del registro GCM. */
    @Id
    @GeneratedValue
    private int id;
    /** El RegId obtenido por GCM. */
    private String registrationId;
    /** El usuario asociado al registro. */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_user")
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
