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
import com.smartpump.dao.interfaces.IUserDao;

@Component
@Path("/users")
public class UserResource {

    @Autowired
    private IUserDao userDao;
    private Gson gson = new Gson();

    @Path("/states")
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Transactional
    public Response getStates(@QueryParam("id") int id) {
        String json = gson.toJson(userDao.getUserState(id));
        return Response.status(201).entity(json).build();
    }

    @Path("/all")
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Transactional
    public Response getAllUsers() {
        String json = gson.toJson(userDao.getUsers());
        return Response.status(201).entity(json).build();
    }

}
