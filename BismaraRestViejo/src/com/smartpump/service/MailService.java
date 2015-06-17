package com.smartpump.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.smartpump.entity.Person;

public class MailService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    // private SimpleMailMessage alertMailMessage;

    @Autowired
    private VelocityEngine velocityEngine;

    public void setMailSender(JavaMailSenderImpl mailSender) {
	this.mailSender = mailSender;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
	this.velocityEngine = velocityEngine;
    }

    // public void setAlertMailMessage(SimpleMailMessage alertMailMessage) {
    // this.alertMailMessage = alertMailMessage;
    // }

    public void sendMail(final String from, final String to, String subject,
	    String body, final Person user) {

	MimeMessagePreparator preparator = new MimeMessagePreparator() {
	    @SuppressWarnings({ "rawtypes", "unchecked" })
	    public void prepare(MimeMessage mimeMessage) throws Exception {
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		message.setTo(to);
		message.setFrom(from);
		Map model = new HashMap();
		model.put("user", user);
		String text = VelocityEngineUtils.mergeTemplateIntoString(
			velocityEngine, "velocity/templateEmail.vm", model);
		message.setText(text, true);
	    }
	};
	this.mailSender.send(preparator);
    }

    // public void sendMail(final String from, final String to, String subject,
    // String body, final Person user) {
    // SimpleMailMessage message = new SimpleMailMessage();
    // message.setFrom(from);
    // message.setTo(to);
    // message.setSubject(subject);
    // message.setText(body);
    // mailSender.send(message);
    // }

    // public void sendAlertMail(String alert) {
    // SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);
    // mailMessage.setText(alert);
    // mailSender.send(mailMessage);
    // }

}
