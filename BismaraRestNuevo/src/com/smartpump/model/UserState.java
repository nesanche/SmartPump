package com.smartpump.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "users_states")
/*
 * @NamedQueries({
 * 
 * @NamedQuery(name = "UserState.findAll", query = "SELECT * FROM users_state"),
 * 
 * @NamedQuery(name = "UserState.findById", query =
 * "SELECT * FROM users_state where id_users_states=:id") })
 */
@XmlRootElement
public class UserState implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
