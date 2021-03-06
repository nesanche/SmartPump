package com.smartpump.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartpump.model.Doctor;
import com.smartpump.model.Patient;
import com.smartpump.security.ResourceFilter;
import com.smartpump.services.DoctorService;
import com.smartpump.utils.RestBoolean;

/**
 * Representa el punto de entrada para la API Rest en el manejo de doctores.
 * 
 * @author Franco Ariel Salonia
 */
@Component
@Path("/doctors")
public class DoctorResource extends AbstractResource {

    /** El servicio relacionado al recurso. */
    @Autowired
    private DoctorService doctorService;

    /**
     * Devuelve un doctor enviando por par�metros en la URL el username y el
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
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getDoctor(@HeaderParam("username") String username,
            @HeaderParam("password") String password) {
        String json = null;
        if (username == null || password == null) {
            return responseBuilder.buildResponse(401);
        }
        Doctor doctor = doctorService.getDoctor(username, password);
        if (doctor == null) {
            return responseBuilder.buildResponse(401);
        } else {
            json = gson.toJson(doctor);
            return responseBuilder.buildResponse(200, json);
        }
    }

    /**
     * Devuelve un m�dico asociado a una matr�cula que se env�a en el
     * encabezado.
     * 
     * @param registrationNumber
     *            la matr�cula.
     * @return el m�dico asociado.
     */
    @Path("/getByRegNumber")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getDoctorByRegistrationNumber(
            @HeaderParam("registrationNumber") String registrationNumber) {
        String json = null;
        Doctor doctor = doctorService.getDoctor(registrationNumber);
        if (doctor == null) {
            return responseBuilder.buildResponse(401);
        } else {
            json = gson.toJson(doctor);
            return responseBuilder.buildResponse(200, json);
        }
    }

    /**
     * Verifica que se pueda utilizar una matr�cula para un nuevo m�dico.
     * 
     * @param registrationNumber
     *            la matr�cula a utilizar, enviada en la URL.
     * @return true si se puede utilizar, false en caso contrario.
     */
    @Path("/verifyRegNumber")
    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public Response verifyRegistrationNumber(
            @QueryParam("regNumber") String registrationNumber) {
        String json = null;
        Doctor doctor = doctorService.getDoctor(registrationNumber);
        RestBoolean response = new RestBoolean(doctor == null);
        return responseBuilder.buildResponse(200, response.toString());
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
    @Produces({ MediaType.APPLICATION_JSON })
    public Response createDoctor(Doctor doctor) {
        Doctor result = doctorService.registerDoctor(doctor);
        String json = gson.toJson(result);
        return responseBuilder.buildResponse(200, json);
    }

    /**
     * Verifica si se puede utilizar una direcci�n de email. Devuelve true si
     * puede ser utilizada, false en caso contrario.
     * 
     * @param email
     *            el email a verificar.
     * @return true si no existe, false en caso contrario.
     */
    @Path("/verifyEmail")
    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public Response verifyEmail(@QueryParam("email") String email) {
        RestBoolean response = new RestBoolean(
                !doctorService.verifyEmail(email));
        return responseBuilder.buildResponse(200, response.toString());
    }

    /**
     * Confirma la cuenta de un doctor enviando el token y el id de usuario por
     * par�metro.
     * 
     * @param id
     *            el id del usuario del doctor.
     * @param token
     *            el contenido del token a verificar.
     * @return true si todo fue exitoso, false en caso contrario.
     */
    @Path("/confirm")
    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public Response confirm(@QueryParam("id") int id,
            @QueryParam("token") String token) {
        RestBoolean response = new RestBoolean(doctorService.confirmDoctor(id,
                token));
        return responseBuilder.buildResponse(200, response.toString());
    }

    /**
     * Devuelve una lista de pacientes asociadas a un doctor, cuyo id se env�a
     * en la cabecera con la clave "doctor-id"
     * 
     * @param doctorId
     *            el id del doctor
     * @return la lista de pacientes asociadas a ese doctor.
     */
    @Path("/patients")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatients(@HeaderParam("doctor-id") int doctorId,
            @HeaderParam("Authorization") String authorization) {
        resourceFilter
                .validateAccess(authorization, ResourceFilter.DOCTOR_ROLE);
        List<Patient> patients = doctorService.getPatientsOfDoctor(doctorId);
        String json = gson.toJson(patients);
        return responseBuilder.buildResponse(200, json);
    }

}
