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

/**
 * Entidad que representa a un doctor registrado en el sistema.
 * 
 * @author Franco Ariel Salonia
 *
 */
@Entity
@Table(name = Tables.DOCTOR_TABLE)
@NamedQueries({
        @NamedQuery(name = Queries.DOCTOR_GET_ALL_QUERY, query = "SELECT d FROM Doctor d"),
        @NamedQuery(name = Queries.DOCTOR_GET_BY_USERNAME_AND_PASSWORD_QUERY, query = "SELECT d FROM Doctor d WHERE d.user.username=:username AND d.user.password=:password"),
        @NamedQuery(name = Queries.DOCTOR_GET_BY_USER_ID, query = "SELECT d FROM Doctor d WHERE d.user.id=:userid"),
        @NamedQuery(name = Queries.DOCTOR_VERIFY_EMAIL_QUERY, query = "SELECT d FROM Doctor d WHERE d.email=:email") })
@XmlRootElement
public class Doctor {

    /** Id autogenerado del doctor. */
    @Id
    @GeneratedValue
    private int id;

    /** Nombre/s de pila */
    private String firstName;
    /** Apellido */
    private String lastName;
    /** Tel�fono de contacto */
    private String phone;
    /** Email de contacto */
    private String email;
    /** N�mero de matr�cula. Debe ser �nico. */
    private int registrationNumber;
    /** Direcci�n donde trabaja el m�dico. */
    private String address;

    /** Usuario que posee ese doctor para ingresar al sistema. */
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_users")
    private User user;

    /**
     * Devuelve el id �nico del doctor.
     * 
     * @return el id �nico del doctor.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id �nico del doctor.
     * 
     * @param id
     *            el id �nico del doctor.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre de pila del doctor.
     * 
     * @return el nombre de pila del doctor.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Establece el nombre de pila del doctor.
     * 
     * @param firstName
     *            el nombre de pila del doctor.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Devuelve el apellido del doctor.
     * 
     * @return el apellido del doctor.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido del doctor.
     * 
     * @param lastName
     *            el apellido del doctor.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Devuelve el tel�fono de contacto del doctor.
     * 
     * @return el tel�fono de contacto del doctor.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Establece el tel�fono de contacto del doctor.
     * 
     * @param phone
     *            el tel�fono de contacto del doctor.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Devuelve el email del doctor.
     * 
     * @return el email del doctor.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del doctor.
     * 
     * @param email
     *            el email del doctor.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Devuelve el usuario del doctor.
     * 
     * @return el usuario del doctor.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario del doctor.
     * 
     * @param user
     *            el usuario del doctor.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Devuelve la matr�cula del doctor.
     * 
     * @return la matr�cula del doctor.
     */
    public int getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Establece la matr�cula del doctor.
     * 
     * @param registrationNumber
     *            la matr�cula del doctor.
     */
    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Devuelve la direcci�n donde trabaja el m�dico.
     * 
     * @return la direcci�n donde trabaja el m�dico.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Establece la direcci�n donde trabaja el m�dico.
     * 
     * @param address
     *            la direcci�n donde trabaja el m�dico.
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
