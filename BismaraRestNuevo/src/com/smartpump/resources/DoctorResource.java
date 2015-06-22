package com.smartpump.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.smartpump.model.Doctor;
import com.smartpump.services.DoctorService;

/**
 * Representa el punto de entrada para la API Rest en el manejo de doctores.
 * 
 * @author Franco Ariel Salonia
 *
 */
@Component
@Path("/doctors")
public class DoctorResource {

    /** El servicio relacionado al recurso. */
    @Autowired
    private DoctorService doctorService;
    /** Atributo encargado del manejo de objetos JSON. */
    private Gson gson = new Gson();

    /**
     * Devuelve un doctor enviando por parámetros en la URL el username y el
     * password.
     * 
     * @param username
     *            el username del doctor.
     * @param password
     *            el password del doctor
     * @return el doctor asociado al username y el password.
     */
    @Path("/getDoctor")
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getDoctor(@QueryParam("username") String username,
            @QueryParam("password") String password) {
        String json = null;
        if (username == null || password == null) {
            return Response.status(401).build();
        }
        Doctor doctor = doctorService.getDoctor(username, password);
        json = gson.toJson(doctor);
        return Response.status(200).entity(json).build();
    }

    /**
     * Crea o actualiza un doctor, enviando los datos en un JSON por Post.
     * 
     * @param doctor
     *            el doctor a almacenar o actualizar.
     * @return el doctor con los datos actualizados.
     */
    @Path("/new")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response createDoctor(Doctor doctor) {
        Doctor result = doctorService.registerDoctor(doctor);
        String json = gson.toJson(result);
        return Response.status(200).entity(json).build();
    }

    /**
     * Verifica la existencia de un email asociado a un doctor. Devuelve ok si
     * existe, denied en caso contrario.
     * 
     * @param email
     *            el email a verificar.
     * @return {'state':'ok'} si existe, {'state':'denied'} en caso contrario.
     */
    @Path("/verifyEmail")
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response verifyEmail(@QueryParam("email") String email) {
        String response = (doctorService.verifyEmail(email)) ? "{'state':'ok'}"
                : "{'state':'denied'}";
        String json = gson.toJson(response);
        return Response.status(200).entity(json).build();
    }

    /**
     * Confirma la cuenta de un doctor enviando el token y el id de usuario por
     * parámetro.
     * 
     * @param id
     *            el id del usuario del doctor.
     * @param token
     *            el contenido del token a verificar.
     * @return {'state':'ok'} si todo fue exitoso, {'state':'denied'} en caso
     *         contrario.
     */
    @Path("/confirm")
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response confirm(@QueryParam("id") String id,
            @QueryParam("token") String token) {
        String response = (doctorService.confirmDoctor(id, token)) ? "{'state':'ok'}"
                : "{'state':'denied'}";
        String json = gson.toJson(response);
        return Response.status(200).entity(json).build();
    }

}
