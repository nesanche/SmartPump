package com.smartpump.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.smartpump.dao.constants.Queries;
import com.smartpump.dao.constants.Tables;

@Entity
@Table(name = Tables.USER_TABLE)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = Queries.USER_GET_ALL_QUERY, query = "SELECT u FROM User u"),
        @NamedQuery(name = Queries.USER_GET_BY_USERNAME_QUERY, query = "SELECT u FROM User u WHERE u.username=:username"),
        @NamedQuery(name = Queries.USER_GET_BY_USERNAME_AND_PASSWORD_QUERY, query = "SELECT u FROM User u WHERE u.username=:username AND u.password=:password") })
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_state")
    private UserState state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

}
