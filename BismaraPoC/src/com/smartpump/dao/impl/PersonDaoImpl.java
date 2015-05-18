package com.smartpump.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;

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

}