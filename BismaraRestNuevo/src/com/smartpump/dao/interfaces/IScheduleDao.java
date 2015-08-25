package com.smartpump.dao.interfaces;

import java.util.List;

import com.smartpump.model.scheduling.Dose;
import com.smartpump.model.scheduling.Pump;
import com.smartpump.model.scheduling.Schedule;

/**
 * Interfaz que provee el comportamiento necesario para el manejo de
 * persistencia de la entidad Schedule.
 * 
 * @author Franco Ariel Salonia
 *
 */
public interface IScheduleDao {

    /**
     * Registra/Actualiza una nueva programaci�n en la base de datos. La
     * devuelve con los datos actualizados.
     * 
     * @param schedule
     *            la programaci�n a persistir.
     * @return la programaci�n con los datos actualizados.
     */
    Schedule registerSchedule(Schedule schedule);

    /**
     * Devuelve una bomba de insulina dependiendo de su id.
     * 
     * @param idPump
     *            el id de la bomba.
     * @return la bomba asociada a ese id.
     */
    Pump getPump(int idPump);

    /**
     * Devuelve una prgramaci�n dependiendo de su id.
     * 
     * @param idSchedule
     *            el id de la programaci�n.
     * @return la programaci�n asociada a ese id.
     */
    Schedule getSchedule(int idSchedule);

    /**
     * Registra/Actualiza una bomba de insulina en la base de datos. La devuelve
     * con los datos actualizados.
     * 
     * @param pump
     *            la bomba a persistir.
     * @return la bomba con lso datos actualizado.s
     */
    Pump registerPump(Pump pump);

    /**
     * Registra/Actualiza una dosis en la base de datos. La devuelve con los
     * datos actualizados.
     * 
     * @param dose
     *            la dosis a persistir.
     * @return la dosis con lso datos actualizado.s
     */
    Dose registerDose(Dose dose);

    /**
     * Obtiene todas las programaciones de un paciente determinado, recibiendo
     * su id por par�metro.
     * 
     * @param idPatient
     *            el id del paciente asociado.
     * @return una lista con las programaciones asociadas al paciente.
     */
    List<Schedule> getSchedulesOfPatient(int idPatient);

    /**
     * Obtiene todas las dosis de una programaci�n en particular.
     * 
     * @param idSchedule
     *            el id de la programaci�n.
     * @return una lista con las dosis asociadas a la programaci�n.
     */
    List<Dose> getDosesOfSchedule(int idSchedule);
}
