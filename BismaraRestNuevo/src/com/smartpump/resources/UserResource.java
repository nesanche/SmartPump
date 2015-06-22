package com.smartpump.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.smartpump.dao.interfaces.IUserDao;

/**
 * Representa el punto de entrada para la API Rest en el manejo de usuarios.
 * 
 * @author Franco Ariel Salonia
 *
 */
@Component
@Path("/users")
public class UserResource {

    /** El Controlador DAO relacionado al recurso. */
    @Autowired
    private IUserDao userDao;
    /** Atributo encargado del manejo de objetos JSON. */
    private Gson gson = new Gson();

    /**
     * Verifica que un nombre de usuario ya ha sido utilizado por algún usuario.
     * 
     * @param username
     *            el nombre de usuario a verificar.
     * @return {'state':'ok'} si existe, {'state':'denied'} en caso contrario.
     */
    @Path("/verifyUsername")
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response verifyUsername(@QueryParam("username") String username) {
        String response = (userDao.validateUser(username)) ? "{'state':'ok'}"
                : "{'state':'denied'}";
        String json = gson.toJson(response);
        return Response.status(200).entity(json).build();
    }

}
