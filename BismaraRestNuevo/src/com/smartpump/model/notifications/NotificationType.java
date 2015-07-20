package com.smartpump.model.notifications;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.smartpump.dao.constants.Tables;

/**
 * Clase que representa un tipo de notificación. Los tipos de notificaciones
 * sirven para que haya eventos disparados en función del tipo recibido. Es
 * fundamental cuando representan alertas grandes, por ejemplo.
 * 
 * @author Franco Ariel Salonia
 *
 */
@Entity
@Table(name = Tables.NOTIFICATION_TYPE_TABLE)
@XmlRootElement
public class NotificationType {

    /** Id auto generado. */
    @Id
    @GeneratedValue
    private int id;
    /** Descripción. */
    private String description;

    /**
     * Constructor por defecto
     */
    public NotificationType() {
    }

    /**
     * Constructor con atributos.
     * 
     * @param id
     *            el id del tipo de notificación.
     * @param description
     *            la descripción del tipo de notificación.
     */
    public NotificationType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * Devuelve el id del tipo de notificación.
     * 
     * @return el id del tipo de notificación.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id del tipo de notificación.
     * 
     * @param id
     *            el id del tipo de notificación.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve la descripción del tipo de notificación.
     * 
     * @return la descripción del tipo de notificación.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del tipo de notificación.
     * 
     * @param description
     *            la descripción del tipo de notificación.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
