package com.smartpump.notifications;

import java.util.Date;

import com.smartpump.model.User;
import com.smartpump.model.notifications.Notification;
import com.smartpump.model.notifications.NotificationType;

/**
 * Entidad responsable de construir notificaciones usando el patr�n Builder.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class NotificationBuilder {
    /** La notificaci�n a construir. */
    private Notification notification;

    /**
     * Crea una nueva notificaci�n y le asigna el timestamp actual.
     * 
     * @return el constructor.
     */
    public NotificationBuilder createNewNotification() {
        notification = new Notification();
        this.notification.setTimestamp(new Date());
        this.notification.setViewed(false);
        return this;
    }

    /**
     * Devuelve la notificaci�n construida finalmente.
     * 
     * @return la notificaci�n construida.
     */
    public Notification build() {
        return this.notification;
    }

    /**
     * Establece el mensaje de la notificaci�n
     * 
     * @param message
     *            el mensaje de la notificaci�n
     * @return el constructor.
     */
    public NotificationBuilder setMessage(String message) {
        this.notification.setMessage(message);
        return this;
    }

    /**
     * Establece el encabezado de la notificaci�n
     * 
     * @param header
     *            el encabezado de la notificaci�n
     * @return el constructor.
     */
    public NotificationBuilder setHeader(String header) {
        this.notification.setHeader(header);
        return this;
    }

    /**
     * Establece el tipo de notificaci�n
     * 
     * @param type
     *            el tipo de la notificaci�n
     * @return el constructor.
     */
    public NotificationBuilder setNotificationType(NotificationType type) {
        this.notification.setNotificationType(type);
        return this;
    }

    /**
     * Establece el usuario de la notificaci�n
     * 
     * @param user
     *            el usuario de la notificaci�n
     * @return el constructor.
     */
    public NotificationBuilder setUser(User user) {
        this.notification.setUser(user);
        return this;
    }

}
