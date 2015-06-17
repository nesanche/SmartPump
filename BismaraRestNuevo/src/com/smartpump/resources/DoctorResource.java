package com.smartpump.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.smartpump.dao.interfaces.IDoctorDao;

@Component
@Path("/doctors")
public class DoctorResource {

    @Autowired
    private IDoctorDao doctorDao;
    private Gson gson = new Gson();

    @Path("/all")
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Transactional
    public Response getStates(@QueryParam("username") String username,
            @QueryParam("password") String password) {
        String json = gson.toJson(doctorDao.getDoctor(username, password));
        return Response.status(201).entity(json).build();
    }
}
