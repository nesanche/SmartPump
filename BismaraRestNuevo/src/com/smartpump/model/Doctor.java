package com.smartpump.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.smartpump.dao.constants.Queries;
import com.smartpump.dao.constants.Tables;

@Entity
@Table(name = Tables.DOCTOR_TABLE)
@NamedQueries({
        @NamedQuery(name = Queries.DOCTOR_GET_ALL_QUERY, query = "SELECT d FROM Doctor d"),
        @NamedQuery(name = Queries.DOCTOR_GET_BY_USERNAME_AND_PASSWORD_QUERY, query = "SELECT d FROM Doctor d WHERE d.user.username=:username AND d.user.password=:password"),
        @NamedQuery(name = Queries.DOCTOR_GET_BY_USER_ID, query = "SELECT d FROM Doctor d WHERE d.user.id=:userid"),
        @NamedQuery(name = Queries.DOCTOR_VERIFY_EMAIL_QUERY, query = "SELECT d FROM Doctor d WHERE d.email=:email") })
@XmlRootElement
public class Doctor {

    @Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private int registrationNumber;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_users")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

}
