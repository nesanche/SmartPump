package com.smartpump.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartpump.dao.interfaces.IScheduleDao;
import com.smartpump.model.scheduling.Pump;
import com.smartpump.model.scheduling.Schedule;

public class ScheduleService {

    @Autowired
    private IScheduleDao scheduleDao;

    public void createSchedule(Schedule schedule, int idPump) {
        Pump pump = scheduleDao.getPump(idPump);
        if (pump == null)
            throw new RuntimeException("No existe una bomba con ese id");
        Schedule scheduleResult = scheduleDao.registerSchedule(schedule);
        pump.addSchedule(scheduleResult);
        scheduleDao.registerPump(pump);
    }
}
