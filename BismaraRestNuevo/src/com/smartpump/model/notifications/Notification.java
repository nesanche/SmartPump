package com.smartpump.model.notifications;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.smartpump.dao.constants.Queries;
import com.smartpump.dao.constants.Tables;
import com.smartpump.model.User;

/**
 * Entidad que representa una notificaci�n determinada. Est� asociada a un
 * usuario determinado y posee algunos valores como "header" (encabezado),
 * "message" (el mensaje propio), "type" el tipo y un bandera para verificar si
 * ha sido vista o no.
 * 
 * @author Franco Ariel Salonia
 *
 */
@Entity
@Table(name = Tables.NOTIFICATION_TABLE)
@NamedQueries({ @NamedQuery(name = Queries.NOTIFICATION_GET_BY_USER_ID, query = "SELECT n FROM Notification n WHERE n.user.id=:userid ORDER BY n.timestamp DESC") })
@XmlRootElement
public class Notification {

    /** El id �nico de la notificaci�n. */
    @Id
    @GeneratedValue
    private int id;
    /** El momento en que se produce. */
    private Date timestamp;
    /** El encabezado. */
    private String header;
    /** El cuerpo de la notificaci�n. */
    private String message;
    /** Una bandera que verifica si ha sido vista o no. */
    private boolean viewed;
    /** El tipo de notificaci�n. */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_type")
    private NotificationType notificationType;
    /** El usuario asociado. */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;

    /**
     * Devuelve el id de la notificaci�n.
     * 
     * @return el id de la notificaci�n.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id de la notificaci�n.
     * 
     * @param id
     *            el id de la notificaci�n.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el momento en que se produjo.
     * 
     * @return el momento en que se produjo.
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Establece el momento en que se produjo.
     * 
     * @param timestamp
     *            el momento en que se produjo.
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Devuelve el encabezado.
     * 
     * @return el encabezado.
     */
    public String getHeader() {
        return header;
    }

    /**
     * Establece el encabezado.
     * 
     * @param header
     *            el encabezado.
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Devuelve el mensaje del cuerpo.
     * 
     * @return el mensaje del cuerpo.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Establece el mensaje del cuerpo.
     * 
     * @param message
     *            el mensaje del cuerpo.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Devuelve si la notificaci�n ha sido vista o no.
     * 
     * @return true si la notificaci�n ya ha sido vista, false en caso
     *         contrario.
     */
    public boolean isViewed() {
        return viewed;
    }

    /**
     * Establece si la notificaci�n ha sido vista o no.
     * 
     * @param viewed
     *            true si la notificaci�n ya ha sido vista, false en caso
     *            contrario.
     */
    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    /**
     * Devuelve el tipo de notificaci�n.
     * 
     * @return el tipo de notificaci�n.
     */
    public NotificationType getNotificationType() {
        return notificationType;
    }

    /**
     * Establece el tipo de notificaci�n.
     * 
     * @param notificationType
     *            el tipo de notificaci�n.
     */
    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    /**
     * Devuelve el usuario asociado.
     * 
     * @return el usuario asociado.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario asociado.
     * 
     * @param user
     *            el usuario asociado.
     */
    public void setUser(User user) {
        this.user = user;
    }

}
