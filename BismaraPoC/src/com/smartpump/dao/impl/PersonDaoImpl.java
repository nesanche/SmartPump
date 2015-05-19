package com.smartpump.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.smartpump.dao.PersonDao;
import com.smartpump.entity.Person;

public class PersonDaoImpl implements PersonDao {
	
	@PersistenceContext(unitName="personUnit")
	private EntityManager entityManager;  

	@Override
	public String getNombre() throws DataAccessException {
		return "Roberto";
	}
	 
	public List<Person> getAll() {		
		return entityManager.createNamedQuery("Person.findAll", Person.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Person createPerson(Person user){		
		Person result = null;
		/* verify if matricula already exists */		
		Query queryFindPerson = entityManager.createNamedQuery("Person.findByMatricula");
		queryFindPerson.setParameter("matricula", user.getMatricula());		
		List<Person> persons = queryFindPerson.getResultList();
		
		if(persons.size() == 0) {
			entityManager.persist(user);
			entityManager.flush();	
			result = user;			
		}
				
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Person login(Person user){		
		Person result = null;
		/* verify if username + matricula + password are ok */		
		Query queryFindPerson = entityManager.createNamedQuery("Person.findForLogin");
		queryFindPerson.setParameter("username", user.getUsername());	
		queryFindPerson.setParameter("matricula", user.getMatricula());	
		queryFindPerson.setParameter("password", user.getPassword());	
		List<Person> persons = queryFindPerson.getResultList();
		
		if(persons.size() == 1) {
			result = user;			
		}
				
		return result;
	}

}