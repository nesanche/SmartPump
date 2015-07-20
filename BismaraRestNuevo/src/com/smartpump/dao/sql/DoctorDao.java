package com.smartpump.dao.sql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.smartpump.dao.constants.Queries;
import com.smartpump.dao.interfaces.IDoctorDao;
import com.smartpump.model.Doctor;
import com.smartpump.model.Patient;

/**
 * Clase que implementa la interfaz IDoctorDao y utiliza los servicios de JPA e
 * Hibernate para persistir.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class DoctorDao extends AbstractDao implements IDoctorDao {

    @Transactional
    @Override
    public Doctor registerDoctor(Doctor doctor) {
        entityManager.persist(doctor);
        entityManager.flush();
        return doctor;
    }

    @Transactional
    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors;
        try {
            doctors = entityManager.createNamedQuery(
                    Queries.DOCTOR_GET_ALL_QUERY, Doctor.class).getResultList();
        } catch (NoResultException ex) {
            doctors = new ArrayList<>();
        }
        return doctors;
    }

    @Transactional
    @Override
    public Doctor getDoctor(String username, String password) {
        TypedQuery<Doctor> query = entityManager
                .createNamedQuery(
                        Queries.DOCTOR_GET_BY_USERNAME_AND_PASSWORD_QUERY,
                        Doctor.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Doctor doctor;
        try {
            doctor = query.getSingleResult();
        } catch (NoResultException e) {
            doctor = null;
        }
        return doctor;
    }

    @Transactional
    @Override
    public boolean verifyEmail(String email) {
        TypedQuery<Doctor> query = entityManager.createNamedQuery(
                Queries.DOCTOR_VERIFY_EMAIL_QUERY, Doctor.class);
        query.setParameter("email", email);
        Doctor doctor = null;
        try {
            doctor = query.getSingleResult();
        } catch (NoResultException ex) {
            doctor = null;
        }
        return doctor != null;
    }

    @Transactional
    @Override
    public Doctor getDoctorByUserId(int id) {
        TypedQuery<Doctor> query = entityManager.createNamedQuery(
                Queries.DOCTOR_GET_BY_USER_ID, Doctor.class);
        query.setParameter("userid", id);
        Doctor doctor = null;
        try {
            doctor = query.getResultList().get(0);
        } catch (NoResultException ex) {
            doctor = null;
        }
        return doctor;
    }

    @Transactional
    @Override
    public Doctor getDoctorByRegistrationNumber(String registrationNumber) {
        TypedQuery<Doctor> query = entityManager.createNamedQuery(
                Queries.DOCTOR_GET_BY_REGISTRATION_NUMBER, Doctor.class);
        query.setParameter("registrationNumber", registrationNumber);
        Doctor doctor = null;
        try {
            doctor = query.getSingleResult();
        } catch (NoResultException ex) {
            doctor = null;
        }
        return doctor;
    }

    @Transactional
    @Override
    public List<Patient> getPatientsOfDoctor(int doctorId) {
        TypedQuery<Patient> query = entityManager.createNamedQuery(
                Queries.PATIENT_GET_PATIENTS_LIST_OF_DOCTOR, Patient.class);
        query.setParameter("doctorid", doctorId);
        List<Patient> patients = null;
        try {
            patients = query.getResultList();
        } catch (NoResultException ex) {
            patients = new ArrayList<>();
        }
        return patients;
    }

}
