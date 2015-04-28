package com.smartpump.service;

import java.util.List;

import com.smartpump.persistent.entity.Person;

public interface PersonService {
	
	public String getNombre();
	
	public List<Person> getAll();

}
