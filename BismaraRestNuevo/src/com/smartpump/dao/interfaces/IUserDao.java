package com.smartpump.dao.interfaces;

import java.util.List;

import com.smartpump.model.User;

/**
 * Interfaz que provee el comportamiento necesario para el manejo de
 * persistencia de la entidad User.
 * 
 * @author Franco Ariel Salonia
 *
 */
public interface IUserDao {

    /**
     * Método que devuelve todos los usuarios persistidos.
     * 
     * @return todos los usuarios persistidos hasta el momento.
     */
    List<User> getUsers();

    /**
     * Método que valida si un determinado nombre de usuario ya existe.
     * 
     * @param username
     *            el nombre de usuario a verificar.
     * @return true si el nombre de usuario ya fue usado, false en caso
     *         contrario.
     */
    boolean validateUser(String username);

}
