package com.smartpump.notifications;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import com.smartpump.dao.interfaces.INotificationDao;
import com.smartpump.model.User;
import com.smartpump.model.notifications.Notification;
import com.smartpump.model.notifications.NotificationType;

/**
 * Entidad responsable de construir la notificación leyendo los mensajes y
 * encabezados por defecto de un archivo de configuración XML.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class NotificationXMLParser {

    /**
     * Entidad responsable del manejo de persistencia de la entidad
     * Notification.
     */
    @Autowired
    private INotificationDao notificationDao;

    /** Entidad que recorre el archivo XML y lee sus propiedades. */
    private XMLConfiguration config;

    /**
     * Constructor por defecto que inicia el atributo "config" leyendo el
     * archivo notifications.xml
     */
    public NotificationXMLParser() {
        try {
            config = new XMLConfiguration("/META-INF/notifications.xml");
        } catch (ConfigurationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Devuelve el objeto de configuración.
     * 
     * @return el objeto de configuración.
     */
    public XMLConfiguration getConfig() {
        return config;
    }

    /**
     * Establece el objeto de configuración.
     * 
     * @param config
     *            el objeto de configuración.
     */
    public void setConfig(XMLConfiguration config) {
        this.config = config;
    }

    /**
     * Método responsable de construir la notificación, recibiendo un tipo y un
     * usuario. Recorre el archivo XML y busca los mensajes y encabezados del
     * tipo de notificación.
     * 
     * @param idType
     *            el tipo de notificación.
     * @param user
     *            el usuario asociado a la notificación.
     * @return la notificación construida.
     */
    public Notification buildNotification(int idType, User user) {
        NotificationType type = notificationDao.getNotificationType(idType);
        int index = idType - 1;
        NotificationBuilder builder = new NotificationBuilder();
        builder.createNewNotification().setHeader(
                config.getString("notifications.notification(" + index
                        + ").header"));
        builder.setMessage(config.getString("notifications.notification("
                + index + ").message"));
        builder.setUser(user).setNotificationType(type);
        return builder.build();
    }
}
