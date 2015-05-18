package com.smartpump.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.smartpump.dao.PersonDao;
import com.smartpump.entity.Person;


@Component
@Path("/myresource")
public class RestService {
	 
		@Autowired
		private PersonDao personDao;
	 
		@Path("/app")
		@GET
		@Produces("text/plain")
		public String getIt() {			
			String nombre = personDao.getNombre();
			return "Hola: " + nombre + " estas usando Google App Engine + Jersey + Spring. Solo te queda JPA!";
		} 	

		
		@Path("/all")
		@GET
		/*@Produces("text/plain")*/
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<Person> getAll() {	
			List<Person> persons = personDao.getAll();
			return persons;
		} 
		
		@Path("/new")
		@POST
		@Consumes({ MediaType.APPLICATION_JSON })
		@Produces({ MediaType.TEXT_HTML })
		@Transactional
		public Response createPerson(Person user) {
			personDao.createPerson(user);
			return Response.status(201).entity("A new doctor has been created!").build();
		}
		
		
		
	}