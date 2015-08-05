package com.smartpump.dao.sql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.smartpump.dao.constants.Queries;
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
        if (notification.getId() == 0) {
            entityManager.persist(notification);
        } else {
            entityManager.merge(notification);
        }
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

    @Override
    @Transactional
    public Notification getNotification(int id) {
        Notification notification;
        try {
            notification = entityManager.find(Notification.class, id);
        } catch (NoResultException ex) {
            notification = null;
        }
        return notification;
    }

    @Override
    @Transactional
    public List<Notification> getNotificationsFromUser(int userId) {
        TypedQuery<Notification> query = entityManager.createNamedQuery(
                Queries.NOTIFICATION_GET_BY_USER_ID, Notification.class);
        query.setParameter("userid", userId);
        List<Notification> notifications;
        try {
            notifications = query.getResultList();
        } catch (NoResultException ex) {
            notifications = new ArrayList<>();
        }
        return notifications;
    }

}
