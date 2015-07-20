package com.smartpump.dao.sql;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import com.smartpump.dao.interfaces.INotificationDao;
import com.smartpump.model.notifications.Notification;
import com.smartpump.model.notifications.NotificationType;

/**
 * Clase que implementa la interfaz INotificationDao y utiliza los servicios de
 * JPA e Hibernate para persistir.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class NotificationDao extends AbstractDao implements INotificationDao {

    @Override
    @Transactional
    public Notification registerNotification(Notification notification) {
        entityManager.persist(notification);
        entityManager.flush();
        return notification;
    }

    @Override
    @Transactional
    public NotificationType getNotificationType(int id) {
        NotificationType notificationType;
        try {
            notificationType = entityManager.find(NotificationType.class, id);
        } catch (NoResultException ex) {
            notificationType = null;
        }
        return notificationType;
    }

}
