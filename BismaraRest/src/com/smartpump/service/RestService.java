package com.smartpump.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.smartpump.dao.PersonDao;
import com.smartpump.entity.Person;

@Component
@Path("/myresource")
public class RestService {

    @Autowired
    private PersonDao personDao;

    Gson gson = new Gson();

    // @Path("/all")
    // @GET
    // @Consumes(MediaType.APPLICATION_JSON)
    // public String getAll() {
    // List<Person> persons = personDao.getAll();
    // String json = gson.toJson(persons);
    // return json;
    // }
    //

    @Path("/new")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Transactional
    public Response createPerson(Person user) {
	Person result = personDao.createPerson(user);
	String json = gson.toJson(result);
	return Response.status(201).entity(json).build();
    }

    @Path("/login")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Transactional
    public Response login(Person user) {
	Person result = personDao.login(user);
	String json = gson.toJson(result);
	return Response.status(201).entity(json).build();
    }

}