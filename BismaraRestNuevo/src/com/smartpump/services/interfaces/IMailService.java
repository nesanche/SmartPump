package com.smartpump.services.interfaces;


public interface IMailService {

    void sendMail(String from, String to, String subject, String msg);

    void sendMail(String from, String[] to, String subject, String msg);
}
