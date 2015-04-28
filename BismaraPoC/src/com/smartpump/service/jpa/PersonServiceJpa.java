package com.smartpump.service.jpa;

import org.springframework.stereotype.Service;

import com.smartpump.persistent.entity.Person;
import com.smartpump.service.PersonService;

import java.util.List;

import javax.jdo.annotations.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service("personService")
public class PersonServiceJpa implements PersonService {
	
	private EntityManager entityManager;
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public String getNombre() {
		return "Roberto";
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Person> getAll() {
		Query query = entityManager.createNamedQuery("Person.findAll");
		List<Person> persons = null;
		persons = query.getResultList();
		return persons;
	}


}
