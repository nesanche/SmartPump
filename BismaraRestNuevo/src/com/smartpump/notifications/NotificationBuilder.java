package com.smartpump.notifications;

import java.util.Date;

import com.smartpump.model.User;
import com.smartpump.model.notifications.Notification;
import com.smartpump.model.notifications.NotificationType;

/**
 * Entidad responsable de construir notificaciones usando el patrón Builder.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class NotificationBuilder {
    /** La notificación a construir. */
    private Notification notification;

    /**
     * Crea una nueva notificación y le asigna el timestamp actual.
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
     * Devuelve la notificación construida finalmente.
     * 
     * @return la notificación construida.
     */
    public Notification build() {
        return this.notification;
    }

    /**
     * Establece el mensaje de la notificación
     * 
     * @param message
     *            el mensaje de la notificación
     * @return el constructor.
     */
    public NotificationBuilder setMessage(String message) {
        this.notification.setMessage(message);
        return this;
    }

    /**
     * Establece el encabezado de la notificación
     * 
     * @param header
     *            el encabezado de la notificación
     * @return el constructor.
     */
    public NotificationBuilder setHeader(String header) {
        this.notification.setHeader(header);
        return this;
    }

    /**
     * Establece el tipo de notificación
     * 
     * @param type
     *            el tipo de la notificación
     * @return el constructor.
     */
    public NotificationBuilder setNotificationType(NotificationType type) {
        this.notification.setNotificationType(type);
        return this;
    }

    /**
     * Establece el usuario de la notificación
     * 
     * @param user
     *            el usuario de la notificación
     * @return el constructor.
     */
    public NotificationBuilder setUser(User user) {
        this.notification.setUser(user);
        return this;
    }

}
