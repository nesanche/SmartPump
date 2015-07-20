package com.smartpump.notifications;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartpump.dao.interfaces.INotificationDao;
import com.smartpump.model.notifications.Notification;

/**
 * Hilo encargado de enviar la notificación al servidor y de guardarla en la
 * base de datos.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class NotificationThread implements Runnable {

    /** La notificación a enviar y persistir. */
    private Notification notification;
    /** El responsable de enviar la notificación. */
    @Autowired
    private NotificationSenderGCM notificationSender;
    /** El responsable de persistir la notificación. */
    @Autowired
    private INotificationDao notificationDao;

    /**
     * Constructor que recibe la notificación por parámetro.
     * 
     * @param notification
     *            la notificación a ser enviada.
     */
    public NotificationThread(Notification notification) {
        this.notification = notification;
    }

    @Override
    public void run() {
        Notification finalNotification = notificationDao
                .registerNotification(this.notification);
        notificationSender.sendNotification(finalNotification);
    }

}
