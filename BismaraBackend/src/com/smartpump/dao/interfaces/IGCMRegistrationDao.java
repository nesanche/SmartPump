package com.smartpump.dao.interfaces;

import com.smartpump.model.notifications.GCMRegistration;

/**
 * Interfaz que provee el comportamiento necesario para el manejo de
 * persistencia de la entidad GCMRegistration.
 * 
 * @author Franco Ariel Salonia
 *
 */
public interface IGCMRegistrationDao {

    /**
     * Obtiene un objeto GCMRegistration en función del id de usuario asociado.
     * 
     * @param userId
     *            el id de usuario asociado.
     * @return el objeto GCMRegistration asociado, null si no existe.
     */
    GCMRegistration getRegistrationByUserId(int userId);

    /**
     * Crea/actualiza un registro de usuario a GCM en la base de datos.
     * 
     * @param registration
     *            el registro a persistir.
     * @return el registro con los datos actualizados.
     */
    GCMRegistration registerUserToGCM(GCMRegistration registration);
}
