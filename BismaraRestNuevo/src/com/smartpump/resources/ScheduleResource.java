package com.smartpump.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartpump.model.scheduling.Schedule;
import com.smartpump.security.ResourceFilter;

/**
 * Representa el punto de entrada para la API Rest en el manejo de
 * programaciones.
 * 
 * @author Franco Ariel Salonia
 */
@Component
@Path("/schedules")
public class ScheduleResource extends AbstractResource {

    /** El servicio relacionado al recurso. */
    @Autowired
    private ScheduleResource scheduleService;

    @Path("/new")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public void createSchedule(Schedule schedule,
            @HeaderParam("Authorization") String authorization,
            @HeaderParam("id-pump") int idPump) {
        resourceFilter
                .validateAccess(authorization, ResourceFilter.DOCTOR_ROLE);
    }

    @Path("/getAll")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public void getSchedules(
            @HeaderParam("Authorization") String authorization,
            @HeaderParam("id-patient") int idPatient) {

    }

}
