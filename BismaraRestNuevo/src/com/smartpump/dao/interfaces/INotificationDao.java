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
     * Crea/actualiza una notificaci�n en la base de datos.
     * 
     * @param notification
     *            la notificaci�n a crear/actualizar.
     * @return la notificaci�n con los datos actualizados.
     */
    Notification registerNotification(Notification notification);

    /**
     * Obtiene un tipo de notificaci�n en funci�n de su id.
     * 
     * @param id
     *            el id del tipo de notificaci�n.
     * @return el tipo de notificaci�n asociado al id.
     */
    NotificationType getNotificationType(int id);

    /**
     * Obtiene una notificaci�n en funci�n de su id.
     * 
     * @param id
     *            el id de la notificaci�n.
     * @return la notificaci�n asociada al id.
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
