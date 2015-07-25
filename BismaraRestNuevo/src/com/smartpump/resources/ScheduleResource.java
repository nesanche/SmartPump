package com.smartpump.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartpump.model.scheduling.Dose;
import com.smartpump.model.scheduling.Schedule;
import com.smartpump.security.ResourceFilter;
import com.smartpump.services.ScheduleService;

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
    private ScheduleService scheduleService;

    @Path("/new")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response createSchedule(Schedule schedule,
            @HeaderParam("Authorization") String authorization,
            @HeaderParam("id-pump") int idPump) {
        resourceFilter
                .validateAccess(authorization, ResourceFilter.DOCTOR_ROLE);
        Schedule result = scheduleService.createSchedule(schedule, idPump);
        String json = gson.toJson(result);
        return responseBuilder.buildResponse(200, json);
    }

    @Path("/doses")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response addDoses(List<Dose> doses,
            @HeaderParam("Authorization") String authorization,
            @HeaderParam("id-schedule") int idSchedule) {
        resourceFilter
                .validateAccess(authorization, ResourceFilter.DOCTOR_ROLE);
        List<Dose> result = scheduleService.addDosesToSchedule(doses,
                idSchedule);
        String json = gson.toJson(result);
        return responseBuilder.buildResponse(200, json);
    }

    @Path("/getAll")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getSchedules(
            @HeaderParam("Authorization") String authorization,
            @HeaderParam("id-patient") int idPatient) {
        resourceFilter
                .validateAccess(authorization, ResourceFilter.DOCTOR_ROLE);
        List<Schedule> schedules = scheduleService.getSchedules(idPatient);
        String json = gson.toJson(schedules);
        return responseBuilder.buildResponse(200, json);
    }

}
