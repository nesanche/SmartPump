package com.smartpump.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartpump.service.Person;

@Service("personDao")
public class PersonDaoImpl implements PersonDao {
	
	protected EntityManager entityManager;
	
	public EntityManager getEntityManager() {
        return entityManager;
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	@Override
	public String getNombre() throws DataAccessException {
		return "Roberto";
	}
	 
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Person> getAll() {
		Query query = entityManager.createNamedQuery("Person.findAll");
		List<Person> persons = null;
		persons = query.getResultList();
		return persons;
	}

}
