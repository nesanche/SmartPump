package com.smartpump.notifications;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartpump.dao.interfaces.INotificationDao;
import com.smartpump.model.notifications.Notification;

/**
 * Hilo encargado de enviar la notificaci�n al servidor y de guardarla en la
 * base de datos.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class NotificationThread implements Runnable {

    /** La notificaci�n a enviar y persistir. */
    private Notification notification;
    /** El responsable de enviar la notificaci�n. */
    @Autowired
    private NotificationSenderGCM notificationSender;
    /** El responsable de persistir la notificaci�n. */
    @Autowired
    private INotificationDao notificationDao;

    /**
     * Constructor que recibe la notificaci�n por par�metro.
     * 
     * @param notification
     *            la notificaci�n a ser enviada.
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
