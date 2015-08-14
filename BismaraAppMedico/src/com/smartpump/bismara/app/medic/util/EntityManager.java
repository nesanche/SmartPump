package com.smartpump.bismara.app.medic.util;

import com.smartpump.bismara.requestmanager.model.Doctor;

public class EntityManager {
    private Doctor doctor;
    private static EntityManager manager;

    private EntityManager() {

    }

    public static EntityManager getInstance() {
        if (manager == null) {
            manager = new EntityManager();
        }
        return manager;
    }

    public void createDoctor(String email) {
        doctor = new Doctor();
        doctor.setEmail(email);
    }

    public Doctor getDoctor() {
        if (doctor == null) {
            doctor = new Doctor();
        }
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
