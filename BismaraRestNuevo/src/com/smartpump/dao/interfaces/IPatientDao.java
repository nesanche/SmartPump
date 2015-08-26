package com.smartpump.dao.interfaces;

import com.smartpump.model.Patient;
import com.smartpump.model.scheduling.Pump;

/**
 * Interfaz que provee el comportamiento necesario para el manejo de
 * persistencia de la entidad Patient.
 * 
 * @author Franco Ariel Salonia
 *
 */
public interface IPatientDao {

    /**
     * Verifica la existencia de un paciente asociado a un mail.
     * 
     * @param email
     *            el mail a verificar.
     * @return true si encontró un paciente con ese mail, false en caso
     *         contrario.
     */
    boolean verifyEmail(String email);

    /**
     * Verifica la existencia de una bomba asociando la dirección MAC con el pin
     * de verificación.
     * 
     * @param macAddress
     *            la dirección MAC de la bomba.
     * @param verificationPin
     *            el pin de verificación.
     * @return la bomba con los datos
     */
    Pump verifyPump(String macAddress, int verificationPin);

    /**
     * Registra un nuevo paciente o actualiza uno existente en la base de datos
     * 
     * @param patient
     *            el paciente a crear o actualizar
     * @return el paciente con los datos actualizados
     */
    Patient registerPatient(Patient patient);

    /**
     * Devuelve un paciente filtrando por el id de bomba.
     * 
     * @param id
     *            el id de la bomba del paciente
     * @return el paciente asociado a ese id de bomba, null si no encontró
     *         ninguno.
     */
    Patient getPatientByPumpId(int id);

    /**
     * Devuelve un paciente filtrando por el id de usuario.
     * 
     * @param id
     *            el id de usuario del paciente
     * @return el paciente asociado a ese id de usuario, null si no encontró
     *         ninguno.
     */
    Patient getPatientByUserId(int id);

}
