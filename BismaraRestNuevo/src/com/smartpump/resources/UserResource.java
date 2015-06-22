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

@Component
@Path("/users")
public class UserResource {

    @Autowired
    private IUserDao userDao;
    private Gson gson = new Gson();

    @Path("/verifyUsername")
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response verifyUsername(@QueryParam("username") String username) {
        String response = (userDao.validateUser(username)) ? "{'state':'ok'}"
                : "{'state':'denied'}";
        String json = gson.toJson(response);
        return Response.status(201).entity(json).build();
    }

}
