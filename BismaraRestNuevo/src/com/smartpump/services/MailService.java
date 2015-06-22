package com.smartpump.services;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.smartpump.services.interfaces.IMailService;

public class MailService implements IMailService {

    private MailSender mailSender;

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
