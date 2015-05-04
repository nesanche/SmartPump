package com.smartpump.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartpump.dao.PersonDao;
import com.smartpump.service.Person;


import javax.ejb.Stateless;


@Stateless
@Path("/myresource")
public class MyResource {
 
	@Autowired
	PersonDao personDao;
 
	/*@GET
	@Produces("text/plain")
	public String getIt() {			
		personService = new PersonServiceJpa();
		String nombre = personService.getNombre();
		return "Hola: " + nombre + " estas usando Google App Engine + Jersey + Spring. Solo te queda JPA!";
	} */
	
	@GET
	@Produces("text/plain")
	public String getAll() {			
		List<Person> persons = personDao.getAll();
		return "La lista es: " + persons;
	} 	
	
	
	
}