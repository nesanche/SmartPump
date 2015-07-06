package com.smartpump.dao.interfaces;

import java.util.List;

import com.smartpump.model.Doctor;
import com.smartpump.model.Patient;

/**
 * Interfaz que provee el comportamiento necesario para el manejo de
 * persistencia de la entidad Doctor.
 * 
 * @author Franco Ariel Salonia
 *
 */
public interface IDoctorDao {
    /**
     * Registra un nuevo doctor o actualiza uno existente en la base de datos.
     * 
     * @param doctor
     *            el doctor a crear o actualizar.
     * @return el doctor con los datos actualizados de la base de datos.
     */
    Doctor registerDoctor(Doctor doctor);

    /**
     * Devuelve un doctor filtrando por el id de usuario.
     * 
     * @param id
     *            el id del usuario.
     * @return un doctor filtrando por el id de usuario, null si no encontró
     *         ninguno.
     */
    Doctor getDoctorByUserId(int id);

    /**
     * Devuelve un doctor filtrando por el número de matrícula.
     * 
     * @param registrationNumber
     *            el número de matrícula.
     * @return un doctor filtrando por el número de matrícula, null si no
     *         encontró ninguno.
     */
    Doctor getDoctorByRegistrationNumber(String registrationNumber);

    /**
     * Devuelve todos los doctores persistidos.
     * 
     * @return toods los doctores persistidos.
     */
    List<Doctor> getAllDoctors();

    /**
     * Devuelve un doctor, filtrando por nombre de usuario y contraseña.
     * 
     * @param username
     *            el nombre de usuario.
     * @param password
     *            la contraseña del usuario.
     * @return el doctor obtenido, null si no encontró ninguno.
     */
    Doctor getDoctor(String username, String password);

    /**
     * Verifica la existencia de un doctor con un email determinado.
     * 
     * @param email
     *            el email a comparar.
     * @return true si existe un doctor con ese email. false en caso contrario.
     */
    boolean verifyEmail(String email);

    /**
     * Devuelve una lista de pacientes de un doctor determinado.
     * 
     * @param doctorId
     *            el id del doctor.
     * @return una lista de pacientes asociados al doctor.
     */
    List<Patient> getPatientsOfDoctor(int doctorId);

}
