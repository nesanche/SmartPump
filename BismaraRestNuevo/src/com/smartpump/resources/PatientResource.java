package com.smartpump.resources;

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

import com.smartpump.model.Patient;
import com.smartpump.services.PatientService;
import com.smartpump.utils.RestBoolean;

/**
 * Representa el punto de entrada para la API Rest en el manejo de pacientes.
 * 
 * @author Franco Ariel Salonia
 *
 */
@Component
@Path("/patients")
public class PatientResource extends AbstractResource {

    /** El servicio relacionado al recurso. */
    @Autowired
    private PatientService patientService;

    /**
     * Verifica si se puede utilizar una dirección de email. Devuelve true si
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
                !patientService.verifyEmail(email));
        return responseBuilder.buildResponse(200, response.toString());
    }

    /**
     * Método responsable de verificar una bomba determinada, enviando su
     * dirección de MAC y su pin de validación.
     * 
     * @param macAddress
     *            la dirección MAC de la bomba.
     * @param verificationPin
     *            el pin de validación de la bomba.
     * @return true si existe una bomba con esa MAC y ese PIN, false en caso
     *         contrario.
     */
    @Path("/verifyPump")
    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public Response verifyPump(@HeaderParam("macAddress") String macAddress,
            @HeaderParam("verificationPin") int verificationPin) {
        RestBoolean response = new RestBoolean(patientService.verifyPump(
                macAddress, verificationPin));
        return responseBuilder.buildResponse(200, response.toString());
    }

    /**
     * Persiste un nuevo paciente en caso que no exista. Lo modifica si existe.
     * 
     * @param patient
     *            el paciente en formato json.
     * @return el paciente con los datos actualizados.
     */
    @Path("/new")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerPatient(Patient patient) {
        Patient result = patientService.registerPatient(patient);
        String json = gson.toJson(result);
        return responseBuilder.buildResponse(200, json);
    }

    /**
     * Confirma la cuenta de un paciente enviando el token y el id de usuario
     * por parámetro.
     * 
     * @param id
     *            el id del usuario del paciente.
     * @param token
     *            el contenido del token a verificar.
     * @return true si todo fue exitoso, false en caso contrario.
     */
    @Path("/confirm")
    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public Response confirm(@QueryParam("id") int id,
            @QueryParam("token") String token) {
        RestBoolean response = new RestBoolean(patientService.confirmPatient(
                id, token));
        return responseBuilder.buildResponse(200, response.toString());
    }
}
