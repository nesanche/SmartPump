package com.smartpump.services;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.smartpump.services.interfaces.IMailService;

/**
 * Implementación del servicio IMailService. Utiliza un bean que se conecta con
 * el servidor SMTP de google.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class MailService implements IMailService {

    /** Entidad encargada de enviar el mail. */
    private MailSender mailSender;

    /**
     * Establece la entidad encargada de enviar el mail.
     * 
     * @param mailSender
     *            la entidad encargada de enviar el mail.
     */
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(String from, String to, String subject, String msg) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);

    }

    @Override
    public void sendMail(String from, String[] to, String subject, String msg) {
        for (int i = 0; i < to.length; i++) {
            sendMail(from, to[i], subject, msg);
        }
    }

}
