package com.smartpump.services.interfaces;

/**
 * Interfaz que representa el servicio asociado al envío y composición de mails.
 * 
 * @author Franco Ariel Salonia.
 *
 */
public interface IMailService {

    /**
     * Método que envía un mail a un destinatario único.
     * 
     * @param from
     *            el mail de origen.
     * @param to
     *            el mail destino.
     * @param subject
     *            el asunto del mail.
     * @param msg
     *            el cuerpo del mail.
     */
    void sendMail(String from, String to, String subject, String msg);

    /**
     * Método que envía un mail a una lista de destinatarios.
     * 
     * @param from
     *            le mail de origen.
     * @param to
     *            un arreglo con la lista de destinatarios.
     * @param subject
     *            el asunto del mail.
     * @param msg
     *            el cuerpo del mail.
     */
    void sendMail(String from, String[] to, String subject, String msg);
}
