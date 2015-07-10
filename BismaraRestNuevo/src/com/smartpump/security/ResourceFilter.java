package com.smartpump.security;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartpump.dao.interfaces.IUserDao;
import com.smartpump.model.User;

public class ResourceFilter {

    public static final int ADMIN_ROLE = 1;
    public static final int DOCTOR_ROLE = 2;
    public static final int PATIENT_ROLE = 3;
    public static final int RESEARCHER_ROLE = 4;
    public static final int INSURANCE_EMPLOYEE_ROLE = 5;

    @Autowired
    private IUserDao userDao;

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
