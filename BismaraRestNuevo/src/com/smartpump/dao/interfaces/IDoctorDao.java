package com.smartpump.dao.interfaces;

import java.util.List;

import com.smartpump.model.Doctor;

public interface IDoctorDao {

    void registerDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();

    Doctor getDoctor(String username, String password);
}
