package com.smartpump.dao.interfaces;

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
    public Notification registerNotification(Notification notification);

    /**
     * Obtiene un tipo de notificación en función de su id.
     * 
     * @param id
     *            el id del tipo de notificación.
     * @return el tipo de notificación asociado al id.
     */
    public NotificationType getNotificationType(int id);

}
