package com.smartpump.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.smartpump.dao.PersonDao;
import com.smartpump.entity.Person;
import com.smartpump.service.MailService;

public class PersonDaoImpl implements PersonDao {

    private MailService mailService;

    // @PersistenceContext(unitName = "personUnit")
    // private EntityManager entityManager;

    @Autowired
    public void setMailService(MailService mailService) {
	this.mailService = mailService;
    }

    // public List<Person> getAll() {
    // return entityManager.createNamedQuery("Person.findAll", Person.class)
    // .getResultList();
    // }

    @SuppressWarnings("unchecked")
    @Transactional
    public Person createPerson(Person user) {
	Person result = null;
	/* verify if matricula already exists */
	// Query queryFindPerson = entityManager
	// .createNamedQuery("Person.findByMatricula");
	// queryFindPerson.setParameter("matricula", user.getMatricula());
	// List<Person> persons = queryFindPerson.getResultList();
	//
	// if (persons.size() == 0) {
	// entityManager.persist(user);
	// entityManager.flush();
	// result = user;

	mailService.sendMail("lucas_borsatto@hotmail.com.ar",
		"borsattolucas@gmail.com",
		"Authorization of User registration", "Aca va el body", user);

	// mailService.sendAlertMail("Exception occurred");

	// }

	return user;
    }

    @Transactional
    public Person login(Person user) {
	// Person result = null;
	/* verify if username + password are ok */
	// Query queryFindPerson = entityManager
	// .createNamedQuery("Person.findForLogin");
	// queryFindPerson.setParameter("username", user.getUsername());
	// queryFindPerson.setParameter("password", user.getPassword());
	// List<Person> persons = queryFindPerson.getResultList();
	//
	// if (persons.size() == 1) {
	// result = user;
	// }

	return user;
    }

}