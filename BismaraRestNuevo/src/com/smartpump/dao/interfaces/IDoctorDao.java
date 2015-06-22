package com.smartpump.dao.interfaces;

import java.util.List;

import com.smartpump.model.Doctor;
import com.smartpump.model.VerificationToken;

public interface IDoctorDao {

    Doctor registerDoctor(Doctor doctor);

    Doctor getDoctorByUserId(int id);

    List<Doctor> getAllDoctors();

    Doctor getDoctor(String username, String password);

    boolean confirmDoctor(String id, String token);

    boolean verifyEmail(String email);

    VerificationToken registerToken(Doctor doctor, String tokenString);
}
