package com.smartpump.bismara.app.patient.util;

import com.smartpump.bismara.requestmanager.model.Patient;


public class EntityManager {
    private Patient patient;
    private static EntityManager manager;
    
    private EntityManager() {
        
    }
    
    public static EntityManager getInstance() {
        if(manager == null) {
            manager = new EntityManager();
        }
        return manager;
    }
    
    public void createPatient(String email) {
        patient = new Patient();
        patient.setEmail(email);
    }
    
    public Patient getPatient() {
        if(patient == null) {
            patient = new Patient();
        }
        return patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
