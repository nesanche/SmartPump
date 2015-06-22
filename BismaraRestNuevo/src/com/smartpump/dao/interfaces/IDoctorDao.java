package com.smartpump.dao.interfaces;

import java.util.List;

import com.smartpump.model.Doctor;
import com.smartpump.model.VerificationToken;

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
     * Encargado de actualizar el estado de un doctor a "Registered" si el token
     * es válido y está dentro de la fecha de expiración.
     * 
     * @param id
     *            el id del usuario del doctor.
     * @param token
     *            el token asignado.
     * @return true si la confirmación fue exitosa. False en caso contrario.
     */
    boolean confirmDoctor(String id, String token);

    /**
     * Verifica la existencia de un doctor con un email determinado.
     * 
     * @param email
     *            el email a comparar.
     * @return true si existe un doctor con ese email. false en caso contrario.
     */
    boolean verifyEmail(String email);

    /**
     * Registra un nuevo VerificationToken en la base de datos.
     * 
     * @param doctor
     *            el doctor asignado al token.
     * @param tokenString
     *            el string generado para ese VerificationToken.
     * @return el objeto persistido con todos los datos.
     */
    VerificationToken registerToken(Doctor doctor, String tokenString);
}
