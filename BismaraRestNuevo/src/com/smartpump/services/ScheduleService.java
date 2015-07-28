package com.smartpump.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartpump.dao.interfaces.IPatientDao;
import com.smartpump.dao.interfaces.IScheduleDao;
import com.smartpump.model.Patient;
import com.smartpump.model.notifications.Notification;
import com.smartpump.model.scheduling.Dose;
import com.smartpump.model.scheduling.Pump;
import com.smartpump.model.scheduling.Schedule;
import com.smartpump.notifications.NotificationThread;
import com.smartpump.notifications.NotificationTypeConstants;
import com.smartpump.notifications.NotificationXMLParser;

/**
 * Servicio asociado a las transacciones que corresponden a la entidad Schedule.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class ScheduleService {

    /** Entidad responsable del manejo de persistencia de la entidad Schedule. */
    @Autowired
    private IScheduleDao scheduleDao;

    /** Entidad responsable del manejo de persistencia de la entidad Patient. */
    @Autowired
    private IPatientDao patientDao;

    /**
     * Entidad encargada de construir la notificaicón recorriendo un archivo xml
     * de configuración.
     */
    @Autowired
    private NotificationXMLParser notificationBuilder;

    /**
     * Crea una nueva programación, la recibe como parámetro junto a la bomba
     * asociada.
     * 
     * @param schedule
     *            la nueva programación.
     * @param idPump
     *            la bomba asociada a la programación.
     */
    public Schedule createSchedule(Schedule schedule, int idPump) {
        Pump pump = scheduleDao.getPump(idPump);
        if (pump == null)
            throw new RuntimeException("No existe una bomba con ese id");
        schedule.setPump(pump);
        scheduleDao.registerSchedule(schedule);
        if (schedule.isConfirmed()) {
            // sendScheduleNotification(idPump);
        }
        return schedule;
    }

    public List<Dose> addDosesToSchedule(List<Dose> doses, int idSchedule) {
        Schedule schedule = scheduleDao.getSchedule(idSchedule);
        if (schedule == null)
            throw new RuntimeException("No existe una prgramación con ese id");
        for (Dose dose : doses) {
            dose.setSchedule(schedule);
            scheduleDao.registerDose(dose);
        }
        return doses;
    }

    /**
     * Método responsable de enviar la notificación al paciente.
     * 
     * @param idPump
     *            el id de la bomba asociada al paciente.
     */
    private void sendScheduleNotification(int idPump) {
        Patient patient = patientDao.getPatientByPumpId(idPump);
        Notification notification = notificationBuilder.buildNotification(
                NotificationTypeConstants.TYPE_NEW_SCHEDULE, patient.getUser());
        NotificationThread notificationThread = new NotificationThread(
                notification);
        Thread thread = new Thread(notificationThread);
        thread.start();
    }

    /**
     * Obtiene las programaciones de un paciente determinado.
     * 
     * @param patientId
     *            el id del paciente.
     * @return una lista con las programaciones del paciente.
     */
    public List<Schedule> getSchedules(int patientId) {
        List<Schedule> schedules = scheduleDao.getSchedulesOfPatient(patientId);
        return schedules;
    }

}
