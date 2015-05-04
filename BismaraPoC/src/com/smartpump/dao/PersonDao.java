package com.smartpump.dao;

import java.util.List;
import com.smartpump.service.Person;
import org.springframework.dao.DataAccessException;

public interface PersonDao {
	
	public String getNombre() throws DataAccessException;
	
	public List<Person> getAll() throws DataAccessException;

}
