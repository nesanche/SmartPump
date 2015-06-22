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

@Component
@Path("/doctors")
public class DoctorResource {

    @Autowired
    private DoctorService doctorService;
    private Gson gson = new Gson();

    @Path("/getDoctor")
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getDoctor(@QueryParam("username") String username,
            @QueryParam("password") String password) {
        String json = null;
        if (username == null || password == null) {
            Response.status(401).build();
        }
        Doctor doctor = doctorService.getDoctor(username, password);
        json = gson.toJson(doctor);
        return Response.status(201).entity(json).build();
    }

    @Path("/new")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response createDoctor(Doctor doctor) {
        Doctor result = doctorService.registerDoctor(doctor);
        String json = gson.toJson(result);
        return Response.status(201).entity(json).build();
    }

    @Path("/verifyEmail")
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response verifyEmail(@QueryParam("email") String email) {
        String response = (doctorService.verifyEmail(email)) ? "{'state':'ok'}"
                : "{'state':'denied'}";
        String json = gson.toJson(response);
        return Response.status(201).entity(json).build();
    }

    @Path("/confirm")
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response confirm(@QueryParam("id") String id,
            @QueryParam("token") String token) {
        String response = (doctorService.confirmDoctor(id, token)) ? "{'state':'ok'}"
                : "{'state':'denied'}";
        String json = gson.toJson(response);
        return Response.status(201).entity(json).build();
    }

}
