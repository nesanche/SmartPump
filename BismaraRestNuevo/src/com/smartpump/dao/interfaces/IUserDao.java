package com.smartpump.dao.interfaces;

import java.util.List;

import com.smartpump.model.User;
import com.smartpump.model.VerificationToken;

/**
 * Interfaz que provee el comportamiento necesario para el manejo de
 * persistencia de la entidad User.
 * 
 * @author Franco Ariel Salonia
 *
 */
public interface IUserDao {

    /**
     * M�todo que devuelve todos los usuarios persistidos.
     * 
     * @return todos los usuarios persistidos hasta el momento.
     */
    List<User> getUsers();

    /**
     * M�todo que valida si un determinado nombre de usuario ya existe.
     * 
     * @param username
     *            el nombre de usuario a verificar.
     * @return true si el nombre de usuario ya fue usado, false en caso
     *         contrario.
     */
    boolean validateUser(String username);

    /**
     * Registra un nuevo VerificationToken en la base de datos.
     * 
     * @param user
     *            el usuario asignado al token.
     * @param tokenString
     *            el string generado para ese VerificationToken.
     * @return el objeto persistido con todos los datos.
     */
    VerificationToken registerToken(User user, String tokenString);

    /**
     * Encargado de actualizar el estado de un usuario a "Registered" si el
     * token es v�lido y est� dentro de la fecha de expiraci�n.
     * 
     * @param id
     *            el id del usuario.
     * @param token
     *            el token asignado.
     * @return true si la confirmaci�n fue exitosa. False en caso contrario.
     */
    boolean confirmUser(int id, String token);

    /**
     * Obtiene un usuario en funci�n del usuario y de la contrase�a.
     * 
     * @param username
     *            el nombre de usuario
     * @param password
     *            la contrase�a
     * @return el usuario asociado. Null si no existe.
     */
    User getUser(String username, String password);

    /**
     * Obtiene un usuario en funci�n del usuario.
     * 
     * @param username
     *            el nombre de usuario
     * @return el usuario asociado. Null si no existe.
     */
    User getUser(String username);

}
