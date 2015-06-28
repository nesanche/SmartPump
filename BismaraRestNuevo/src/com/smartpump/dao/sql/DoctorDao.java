package com.smartpump.dao.sql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.smartpump.dao.constants.Queries;
import com.smartpump.dao.constants.Units;
import com.smartpump.dao.interfaces.IDoctorDao;
import com.smartpump.model.Doctor;
import com.smartpump.model.User;
import com.smartpump.model.UserState;
import com.smartpump.model.VerificationToken;

/**
 * Clase que implementa la interfaz IDoctorDao y utiliza los servicios de JPA e
 * Hibernate para persistir.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class DoctorDao implements IDoctorDao {

    /** Atributo encargado del manejo de persistencia. */
    @PersistenceContext(unitName = Units.USER_UNIT, type = PersistenceContextType.TRANSACTION)
    @Autowired
    private EntityManager entityManager;

    /**
     * Establece el manejador de entidades para la persistencia.
     * 
     * @param entityManager
     *            el manejador de entidades.
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Doctor registerDoctor(Doctor doctor) {
        entityManager.persist(doctor);
        entityManager.flush();
        return doctor;
    }

    @Transactional
    @Override
    public VerificationToken registerToken(Doctor doctor, String tokenString) {
        VerificationToken token = new VerificationToken(tokenString,
                doctor.getUser());
        token.getUser().setId(doctor.getUser().getId());
        entityManager.persist(token);
        entityManager.flush();
        return token;
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
        Query query = entityManager
                .createNamedQuery(
                        Queries.DOCTOR_GET_BY_USERNAME_AND_PASSWORD_QUERY,
                        Doctor.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Doctor doctor;
        try {
            doctor = (Doctor) query.getSingleResult();
        } catch (NoResultException e) {
            doctor = null;
        }
        return doctor;
    }

    @Transactional
    @Override
    public boolean confirmDoctor(String id, String token) {
        Query query = entityManager.createNamedQuery(
                Queries.VERIFICATION_TOKEN_CONFIRM_USER_QUERY,
                VerificationToken.class);
        query.setParameter("id", Integer.parseInt(id));
        query.setParameter("token", token);
        VerificationToken verificationToken = null;
        try {
            verificationToken = (VerificationToken) query.getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        Date today = new Date();
        if (today.after(verificationToken.getExpirationDate())) {
            return false;
        }
        User user = verificationToken.getUser();
        user.setState(new UserState(2, "Registered"));
        entityManager.persist(user);
        entityManager.flush();
        return true;
    }

    @Transactional
    @Override
    public boolean verifyEmail(String email) {
        Query query = entityManager.createNamedQuery(
                Queries.DOCTOR_VERIFY_EMAIL_QUERY, Doctor.class);
        query.setParameter("email", email);
        Doctor doctor = null;
        try {
            doctor = (Doctor) query.getSingleResult();
        } catch (NoResultException ex) {
            doctor = null;
        }
        return doctor != null;
    }

    @Transactional
    @Override
    public Doctor getDoctorByUserId(int id) {
        Query query = entityManager.createNamedQuery(
                Queries.DOCTOR_GET_BY_USER_ID, Doctor.class);
        query.setParameter("userid", id);
        Doctor doctor = null;
        try {
            doctor = (Doctor) query.getResultList().get(0);
        } catch (NoResultException ex) {
            doctor = null;
        }
        return doctor;
    }

}
