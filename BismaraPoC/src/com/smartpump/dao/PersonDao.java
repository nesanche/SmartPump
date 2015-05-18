package com.smartpump.dao;

import java.util.List;

import com.smartpump.entity.Person;

public interface PersonDao {
	
	public String getNombre();
	
	public List<Person> getAll();
	
	public Long createPerson(Person user);

}
