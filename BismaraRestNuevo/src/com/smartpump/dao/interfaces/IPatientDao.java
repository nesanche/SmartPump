package com.smartpump.dao.interfaces;

import com.smartpump.model.Patient;

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
     * @return true si encontr� un paciente con ese mail, false en caso
     *         contrario.
     */
    boolean verifyEmail(String email);

    /**
     * Verifica la existencia de una bomba asociando la direcci�n MAC con el pin
     * de verificaci�n.
     * 
     * @param macAddress
     *            la direcci�n MAC de la bomba.
     * @param verificationPin
     *            el pin de verificaci�n.
     * @return true si encontr� una bomba asociada a esos datos, false en caso
     *         contrario.
     */
    boolean verifyPump(String macAddress, int verificationPin);

    /**
     * Registra un nuevo paciente o actualiza uno existente en la base de datos
     * 
     * @param patient
     *            el paciente a crear o actualizar
     * @return el paciente con los datos actualizados
     */
    Patient registerPatient(Patient patient);

    /**
     * Devuelve un paciente filtrando por el id de usuario.
     * 
     * @param id
     *            el id de usuario del paciente
     * @return el paciente asociado a ese id de usuario, nill si no encontr�
     *         ninguno.
     */
    Patient getPatientByUserId(int id);

}
