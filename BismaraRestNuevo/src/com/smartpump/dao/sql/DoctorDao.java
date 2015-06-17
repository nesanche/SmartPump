package com.smartpump.dao.sql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.smartpump.dao.interfaces.IDoctorDao;
import com.smartpump.model.Doctor;

public class DoctorDao implements IDoctorDao {

    @PersistenceContext(unitName = "userUnit")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void registerDoctor(Doctor doctor) {
        entityManager.persist(doctor);
    }

    @Transactional
    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = entityManager.createNamedQuery("Doctor.getAll",
                Doctor.class).getResultList();
        return doctors;
    }

    @Transactional
    @Override
    public Doctor getDoctor(String username, String password) {
        Query query = entityManager.createNamedQuery(
                "Doctor.getByUsernameAndPassword", Doctor.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Doctor doctor = (Doctor) query.getSingleResult();
        return doctor;
    }

}
