package com.smartpump.dao;

import com.smartpump.entity.Person;

public interface PersonDao {

    // public List<Person> getAll();
    //
    public Person createPerson(Person user);

    public Person login(Person user);

}
