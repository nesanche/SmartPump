package com.smartpump.dao.interfaces;

import java.util.List;

import com.smartpump.model.scheduling.Pump;
import com.smartpump.model.scheduling.Schedule;

public interface IScheduleDao {

    Schedule registerSchedule(Schedule schedule);

    Pump getPump(int idPump);

    Pump registerPump(Pump pump);

    List<Schedule> getSchedulesOfPatient(int idPatient);
}
