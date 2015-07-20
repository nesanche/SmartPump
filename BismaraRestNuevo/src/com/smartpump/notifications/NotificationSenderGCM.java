package com.smartpump.notifications;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartpump.dao.interfaces.IGCMRegistrationDao;
import com.smartpump.model.notifications.GCMRegistration;
import com.smartpump.model.notifications.Notification;

/**
 * Entidad responsable de enviar al servidor GCM la notificación construida.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class NotificationSenderGCM {

    /** URL para enviar a GCM */
    private static final String GCM_URL = "https://android.googleapis.com/gcm/send";
    /** Clave del proyecto GCM. */
    private static final String GCM_API_KEY = "AIzaSyCEXGHLB-545H70oxcbCBhaP57RKSyOxWw";

    /**
     * Entidad responsable del manejo de persistencia del objeto
     * GCMRegistration.
     */
    @Autowired
    private IGCMRegistrationDao registrationDao;

    /**
     * Envía la notificación construida al servidor GCM para que los usuarios la
     * reciban.
     * 
     * @param notification
     *            la notificación a enviar.
     */
    public void sendNotification(Notification notification) {
        GCMRegistration registration = registrationDao
                .getRegistrationByUserId(notification.getUser().getId());
        NotificationContent content = buildContent(notification,
                registration.getRegistrationId());
        send(content);
    }

    /**
     * Construye el contenido a ser enviado al servidor.
     * 
     * @param notification
     *            la notificación de donde se extrae el contenido.
     * @param regId
     *            el id del receptor.
     * @return el objeto que alberga el contenido de la notificación.
     */
    private NotificationContent buildContent(Notification notification,
            String regId) {
        NotificationContent notificationContent = new NotificationContent();
        notificationContent.setRegistrationId(regId);
        notificationContent.addData("type", notification.getNotificationType()
                .getId() + "");
        notificationContent.addData("header", notification.getHeader());
        notificationContent.addData("message", notification.getMessage());
        return notificationContent;
    }

    /**
     * Método que se conecta con el servidor GCM y envía el contenido de la
     * notificación. Imprime por consola la respuesta del servidor.
     * 
     * @param content
     *            el contenido a enviar en la request.
     */
    private void send(NotificationContent content) {
        try {
            // 1. URL
            URL url = new URL(GCM_URL);
            // 2. Open connection
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            // 3. Specify POST method
            connection.setRequestMethod("POST");
            // 4. Set the headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection
                    .setRequestProperty("Authorization", "key=" + GCM_API_KEY);
            connection.setDoOutput(true);
            // 5. Add JSON data into POST request body
            // `5.1 Use Jackson object mapper to convert Contnet object into
            // JSON
            ObjectMapper mapper = new ObjectMapper();
            // 5.2 Get connection output stream
            DataOutputStream dataOutputStream = new DataOutputStream(
                    connection.getOutputStream());
            // 5.3 Copy Content "JSON" into
            mapper.writeValue(dataOutputStream, content);
            // 5.4 Send the request
            dataOutputStream.flush();
            // 5.5 close
            dataOutputStream.close();
            // 6. Get the response
            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // 7. Print result
            System.out.println(response.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Entidad que representa el contenido de la notificación, que es enviado al
     * servidor.
     * 
     * @author Franco Ariel Salonia
     *
     */
    private class NotificationContent implements Serializable {
        /** Id para serialización */
        private static final long serialVersionUID = -1511412969630985595L;
        /** Id del registro GCM a donde se envía la notificación. */
        private List<String> registration_ids;
        /** Datos a enviar en el contenido. */
        private Map<String, String> data;

        /**
         * Establece el id de registro del usuario.
         * 
         * @param registrationId
         *            el id del registro del usuario.
         */
        public void setRegistrationId(String registrationId) {
            if (registration_ids == null)
                registration_ids = new LinkedList<String>();
            registration_ids.add(registrationId);
        }

        /**
         * Agrega datos a enviar en el contenido.
         * 
         * @param key
         *            clave del dato.
         * @param value
         *            valor del dato.
         */
        public void addData(String key, String value) {
            if (data == null)
                data = new HashMap<String, String>();
            data.put(key, value);
        }

    }
}
