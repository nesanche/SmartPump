package com.smartpump.services.interfaces;

/**
 * Interfaz que representa el servicio asociado al env�o y composici�n de mails.
 * 
 * @author Franco Ariel Salonia.
 *
 */
public interface IMailService {

    /**
     * M�todo que env�a un mail a un destinatario �nico.
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
     * M�todo que env�a un mail a una lista de destinatarios.
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
