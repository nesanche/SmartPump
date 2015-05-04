package com.smartpump.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Person")
@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
public class Person{

	@Id
    @Column(name = "id")
	private int id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "edad")
	private int edad;

	public void setId(int id) {
		this.id = id;
	}

	@Id
	public int getId() {
		return id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getEdad() {
		return edad;
	}
	
}
