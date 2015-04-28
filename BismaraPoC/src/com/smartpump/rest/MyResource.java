package com.smartpump.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.smartpump.persistent.entity.Person;
import com.smartpump.service.PersonService;
import com.smartpump.service.jpa.PersonServiceJpa;

@Path("/myresource")
@Component
@Scope("request")
public class MyResource {
 
	@Autowired
	PersonService personService;
 
	/*@GET
	@Produces("text/plain")
	public String getIt() {			
		personService = new PersonServiceJpa();
		String nombre = personService.getNombre();
		return "Hola: " + nombre + " estas usando Google App Engine + Jersey + Spring. Solo te queda JPA!";
	} */	
	@GET
	@Produces("text/plain")
	@Path("/todos")
	public String getAll() {			
		personService = new PersonServiceJpa();
		List<Person> persons = personService.getAll();
		return "La lista es: " + persons;
	} 
	
}