package com.smartpump.dao.sql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.smartpump.dao.constants.Queries;
import com.smartpump.dao.interfaces.IScheduleDao;
import com.smartpump.model.scheduling.Dose;
import com.smartpump.model.scheduling.Pump;
import com.smartpump.model.scheduling.Schedule;

/**
 * Clase que implementa la interfaz IScheduleDao y utiliza los servicios de JPA
 * e Hibernate para persistir.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class ScheduleDao extends AbstractDao implements IScheduleDao {

    @Transactional
    @Override
    public Schedule registerSchedule(Schedule schedule) {
        entityManager.persist(schedule);
        entityManager.flush();
        return schedule;
    }

    @Transactional
    @Override
    public Pump getPump(int idPump) {
        Pump pump;
        try {
            pump = entityManager.find(Pump.class, idPump);
        } catch (NoResultException ex) {
            pump = null;
        }
        return pump;
    }

    @Transactional
    @Override
    public Schedule getSchedule(int idSchedule) {
        Schedule schedule;
        try {
            schedule = entityManager.find(Schedule.class, idSchedule);
        } catch (NoResultException ex) {
            schedule = null;
        }
        return schedule;
    }

    @Transactional
    @Override
    public Pump registerPump(Pump pump) {
        entityManager.persist(pump);
        entityManager.flush();
        return pump;
    }

    @Transactional
    @Override
    public Dose registerDose(Dose dose) {
        entityManager.persist(dose);
        entityManager.flush();
        return dose;
    }

    @Transactional
    @Override
    public List<Schedule> getSchedulesOfPatient(int idPatient) {
        TypedQuery<Schedule> query = entityManager.createNamedQuery(
                Queries.PATIENT_GET_SCHEDULES, Schedule.class);
        query.setParameter("idPatient", idPatient);
        List<Schedule> schedules;
        try {
            schedules = query.getResultList();
        } catch (NoResultException e) {
            schedules = new ArrayList<>();
        }
        return schedules;
    }

}
