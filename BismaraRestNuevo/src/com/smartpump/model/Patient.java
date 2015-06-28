package com.smartpump.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.smartpump.dao.constants.Queries;
import com.smartpump.dao.constants.Tables;

/**
 * Clase que representa un objeto paciente dentro del sistema.
 * 
 * @author Franco Ariel Salonia
 *
 */
@Entity
@Table(name = Tables.PATIENT_TABLE)
@NamedQueries({
        @NamedQuery(name = Queries.PATIENT_VERIFY_EMAIL_QUERY, query = "SELECT p FROM Patient p WHERE p.email=:email"),
        @NamedQuery(name = Queries.PATIENT_GET_BY_USER_ID, query = "SELECT p FROM Patient p WHERE p.user.id=:userid"),
        @NamedQuery(name = Queries.PATIENT_GET_PATIENTS_LIST_OF_DOCTOR, query = "SELECT p FROM Patient p WHERE p.doctor.id=:doctorid") })
@XmlRootElement
public class Patient {

    /** Id autogenerado */
    @Id
    @GeneratedValue
    private int id;
    /** Nombre de pila */
    private String firstName;
    /** Apellido */
    private String lastName;
    /** Teléfono principal de contacto */
    private String phoneOne;
    /** Teléfono secundario de contacto */
    private String phoneTwo;
    /** Email */
    private String email;
    /** Dirección de residencia */
    private String address;
    /** Usuario que posee ese paciente para ingresar al sistema. */
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_user")
    private User user;
    /** Doctor asociado al paciente. */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;
    /** Bomba del paciente. */
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_pump")
    private Pump pump;

    /**
     * Devuelve el id autogenerado del paciente.
     * 
     * @return el id autogenerado del paciente.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id autogenerado del paciente.
     * 
     * @param id
     *            el id autogenerado del paciente.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre de pila del paciente.
     * 
     * @return el nombre de pila del paciente.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Establece el nombre de pila del paciente.
     * 
     * @param firstName
     *            el nombre de pila del paciente.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Devuelve el apellido del paciente.
     * 
     * @return el apellido del paciente.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido del paciente.
     * 
     * @param lastName
     *            el apellido del paciente.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Devuelve el primer teléfono de contacto.
     * 
     * @return el primer teléfono de contacto.
     */
    public String getPhoneOne() {
        return phoneOne;
    }

    /**
     * Establece el primer teléfono de contacto.
     * 
     * @param phoneOne
     *            el primer teléfono de contacto.
     */
    public void setPhoneOne(String phoneOne) {
        this.phoneOne = phoneOne;
    }

    /**
     * Devuelve el segundo teléfono de contacto.
     * 
     * @return el segundo teléfono de contacto.
     */
    public String getPhoneTwo() {
        return phoneTwo;
    }

    /**
     * Establece el segundo teléfono de contacto.
     * 
     * @param phoneTwo
     *            el segundo teléfono de contacto.
     */
    public void setPhoneTwo(String phoneTwo) {
        this.phoneTwo = phoneTwo;
    }

    /**
     * Devuelve el email del paciente.
     * 
     * @return el email del paciente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del paciente.
     * 
     * @param email
     *            el email del paciente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Devuelve la dirección del paciente.
     * 
     * @return la dirección del paciente.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Establece la dirección del paciente.
     * 
     * @param address
     *            la dirección del paciente.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Devuelve el usuario del paciente.
     * 
     * @return el usuario del paciente.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario del paciente.
     * 
     * @param user
     *            el usuario del paciente.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Devuelve el doctor del paciente.
     * 
     * @return el doctor del paciente.
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Establece el doctor del paciente.
     * 
     * @param doctor
     *            el doctor del paciente.
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Devuelve la bomba del paciente.
     * 
     * @return la bomba del paciente.
     */
    public Pump getPump() {
        return pump;
    }

    /**
     * Establece la bomba del paciente.
     * 
     * @param pump
     *            la bomba del paciente.
     */
    public void setPump(Pump pump) {
        this.pump = pump;
    }

}
