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
     * Crea/actualiza una notificaci�n en la base de datos.
     * 
     * @param notification
     *            la notificaci�n a crear/actualizar.
     * @return la notificaci�n con los datos actualizados.
     */
    public Notification registerNotification(Notification notification);

    /**
     * Obtiene un tipo de notificaci�n en funci�n de su id.
     * 
     * @param id
     *            el id del tipo de notificaci�n.
     * @return el tipo de notificaci�n asociado al id.
     */
    public NotificationType getNotificationType(int id);

}