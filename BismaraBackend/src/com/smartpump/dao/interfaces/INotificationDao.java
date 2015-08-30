package com.smartpump.dao.interfaces;

import java.util.List;

import com.smartpump.model.notifications.Notification;
import com.smartpump.model.notifications.NotificationType;

/**
 * Interfaz que provee el comportamiento necesario para el manejo de
 * persistencia de la entidad Notification.
 * 
 * @author Franco Ariel Salonia
 *
 */
public interface INotificationDao {

    /**
     * Crea/actualiza una notificación en la base de datos.
     * 
     * @param notification
     *            la notificación a crear/actualizar.
     * @return la notificación con los datos actualizados.
     */
    Notification registerNotification(Notification notification);

    /**
     * Obtiene un tipo de notificación en función de su id.
     * 
     * @param id
     *            el id del tipo de notificación.
     * @return el tipo de notificación asociado al id.
     */
    NotificationType getNotificationType(int id);

    /**
     * Obtiene una notificación en función de su id.
     * 
     * @param id
     *            el id de la notificación.
     * @return la notificación asociada al id.
     */
    Notification getNotification(int id);

    /**
     * Obtiene la lista de notificaciones de un usuario.
     * 
     * @param userId
     *            el id de usuario.
     * @return una lista de notificaciones ordenada por timestamp.
     */
    List<Notification> getNotificationsFromUser(int userId);

}
