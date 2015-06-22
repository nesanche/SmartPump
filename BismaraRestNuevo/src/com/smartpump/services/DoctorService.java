package com.smartpump.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartpump.dao.interfaces.IDoctorDao;
import com.smartpump.model.Doctor;
import com.smartpump.model.VerificationToken;
import com.smartpump.services.interfaces.IMailService;

public class DoctorService {

    private static final String FROM_EMAIL = "no.reply.bismara@gmail.com";

    private static final String TO_MAIL = "bismara.staff@gmail.com";

    private static final String TOKEN_POSSIBLE_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final String CONFIRM_URL = "http://bismara.elasticbeanstalk.com/rest/doctors/confirm";
    @Autowired
    private IDoctorDao doctorDao;

    @Autowired
    private IMailService mailService;

    public Doctor registerDoctor(Doctor doctor) {
        Doctor result = doctorDao.registerDoctor(doctor);
        VerificationToken token = doctorDao.registerToken(doctor,
                generateToken());
        sendToVerifyMail(result, token);
        return result;
    }

    public Doctor getDoctor(String username, String password) {
        Doctor doctor = doctorDao.getDoctor(username, password);
        return doctor;
    }

    public boolean confirmDoctor(String id, String token) {
        boolean result = doctorDao.confirmDoctor(id, token);
        if (result) {
            Doctor doctor = doctorDao.getDoctorByUserId(Integer.parseInt(id));
            sendConfirmationMail(doctor);
        }
        return result;
    }

    public boolean verifyEmail(String email) {
        return doctorDao.verifyEmail(email);
    }

    private void sendToVerifyMail(Doctor doctor, VerificationToken token) {
        mailService.sendMail(FROM_EMAIL, TO_MAIL, "Confirma",
                getToVerifyMailBody(doctor, token));
    }

    private String getToVerifyMailBody(Doctor doctor, VerificationToken token) {
        StringBuilder builder = new StringBuilder(
                "Hemos recibido un nuevo registro de doctor. Sus datos son:\n");
        builder.append("Apellido y nombre: " + doctor.getLastName() + ", "
                + doctor.getFirstName() + "\n");
        builder.append("Matrícula: " + doctor.getRegistrationNumber() + "\n");
        builder.append("Email: " + doctor.getEmail() + "\n");
        builder.append("Teléfono: " + doctor.getPhone() + "\n");
        builder.append("Nombre de usuario: " + doctor.getUser().getUsername()
                + "\n");
        builder.append("Para confirmar su registración por favor hacer click aquí:"
                + "\n");
        builder.append(CONFIRM_URL + "?id=" + doctor.getUser().getId()
                + "&token=" + token.getToken() + "\n\n");
        builder.append("Muchas gracias,\n");
        builder.append("Bismara system");
        return builder.toString();
    }

    private void sendConfirmationMail(Doctor doctor) {
        mailService
                .sendMail(
                        FROM_EMAIL,
                        doctor.getEmail(),
                        "Tu cuenta ha sido activada",
                        "¡Felicitaciones! Tu cuenta ha sido activada. Ya puedes comenzar a utilizar los servicios de Bismara.");
    }

    private String generateToken() {
        Random rand = new Random();
        String possibleLetters = TOKEN_POSSIBLE_CHARACTERS;
        StringBuilder stringBuilder = new StringBuilder(20);
        for (int i = 0; i < 20; i++)
            stringBuilder.append(possibleLetters.charAt(rand
                    .nextInt(possibleLetters.length())));
        return stringBuilder.toString();
    }

}
