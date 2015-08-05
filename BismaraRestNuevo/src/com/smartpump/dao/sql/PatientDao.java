package com.smartpump.dao.sql;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.smartpump.dao.constants.Queries;
import com.smartpump.dao.interfaces.IPatientDao;
import com.smartpump.model.Patient;
import com.smartpump.model.scheduling.Pump;

/**
 * Clase que implementa la interfaz IPatientDao y utiliza los servicios de JPA e
 * Hibernate para persistir.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class PatientDao extends AbstractDao implements IPatientDao {

    @Transactional
    @Override
    public boolean verifyEmail(String email) {
        TypedQuery<Patient> query = entityManager.createNamedQuery(
                Queries.PATIENT_VERIFY_EMAIL_QUERY, Patient.class);
        query.setParameter("email", email);
        Patient patient = null;
        try {
            patient = query.getSingleResult();
        } catch (NoResultException ex) {
            patient = null;
        }
        return patient != null;
    }

    @Transactional
    @Override
    public boolean verifyPump(String macAddress, int verificationPin) {
        TypedQuery<Pump> query = entityManager.createNamedQuery(
                Queries.PUMP_GET_BY_MAC_ADDRESS, Pump.class);
        query.setParameter("macAddress", macAddress);
        Pump pump = null;
        try {
            pump = query.getSingleResult();
        } catch (NoResultException ex) {
            return false;
        }
        return (pump.getVerificationPin() == verificationPin);
    }

    @Transactional
    @Override
    public Patient registerPatient(Patient patient) {
        if (patient.getId() == 0) {
            entityManager.persist(patient);
        } else {
            entityManager.merge(patient);
        }
        entityManager.flush();
        return patient;
    }

    @Transactional
    @Override
    public Patient getPatientByUserId(int id) {
        TypedQuery<Patient> query = entityManager.createNamedQuery(
                Queries.PATIENT_GET_BY_USER_ID, Patient.class);
        query.setParameter("userid", id);
        Patient patient = null;
        try {
            patient = query.getResultList().get(0);
        } catch (NoResultException ex) {
            patient = null;
        }
        return patient;
    }

    @Transactional
    @Override
    public Patient getPatientByPumpId(int id) {
        TypedQuery<Patient> query = entityManager.createNamedQuery(
                Queries.PATIENT_GET_BY_PUMP_ID, Patient.class);
        query.setParameter("idPump", id);
        Patient patient = null;
        try {
            patient = query.getResultList().get(0);
        } catch (NoResultException ex) {
            patient = null;
        }
        return patient;
    }

}
