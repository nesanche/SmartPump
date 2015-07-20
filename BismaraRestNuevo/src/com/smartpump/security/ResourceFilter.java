package com.smartpump.security;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartpump.dao.interfaces.IUserDao;
import com.smartpump.model.User;

/**
 * Entidad responsable de filtrar los recursos por el rol de los usuarios. Posee
 * todos los roles asociados y verifica que sea el adecuado según el recurso.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class ResourceFilter {
    /** Rol de usuario administrador */
    public static final int ADMIN_ROLE = 1;
    /** Rol de usuario médico */
    public static final int DOCTOR_ROLE = 2;
    /** Rol de usuario paciente */
    public static final int PATIENT_ROLE = 3;
    /** Rol de usuario investigador */
    public static final int RESEARCHER_ROLE = 4;
    /** Rol de usuario de obra social */
    public static final int INSURANCE_EMPLOYEE_ROLE = 5;
    /** Entidad responsable del manejo de datos de los usuarios. */
    @Autowired
    private IUserDao userDao;

    /**
     * Método que valida si un usuario puede acceder a un recurso o no. Recibe
     * una cadena que representa usuario:password en base64 y el rol que ese
     * usuario debiese tener para estar autorizado.
     * 
     * @param authorization
     *            usuario:password en base64.
     * @param role
     *            el rol que ese usuario debiese tener.
     */
    public void validateAccess(String authorization, int role) {
        if (role == 0)
            return;
        authorization = authorization.substring(6);
        User user = getUser(authorization);
        if (user == null)
            throw new RuntimeException(
                    "No se encontró un usuario con esas credenciales");
        if (user.getRole().getId() != role) {
            throw new RuntimeException(
                    "El usuario no tiene los permisos para ingresar a esta parte del sistema");
        }
    }

    /**
     * Obtiene el usuario asociado al usuario y password recibidos.
     * 
     * @param authorization
     *            usuario:password en base64.
     * @return el usuario asociado. Null si no fue encontrado.
     */
    private User getUser(String authorization) {
        byte[] authorizationBytes = authorization
                .getBytes(StandardCharsets.UTF_8);
        byte[] decodedAuthorization = Base64.getDecoder().decode(
                authorizationBytes);
        String authorizationData = null;
        try {
            authorizationData = new String(decodedAuthorization, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        String[] splittedData = authorizationData.split(":");
        User user = userDao.getUser(splittedData[0], splittedData[1]);
        return user;
    }
}
