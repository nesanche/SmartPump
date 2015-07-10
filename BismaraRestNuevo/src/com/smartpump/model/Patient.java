package com.smartpump.model;

import java.util.Date;

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
import com.smartpump.model.scheduling.Treatment;

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
        @NamedQuery(name = Queries.PATIENT_GET_PATIENTS_LIST_OF_DOCTOR, query = "SELECT p FROM Patient p WHERE p.doctor.id=:doctorid"),
        @NamedQuery(name = Queries.PATIENT_GET_SCHEDULES, query = "SELECT p.treatment.pump.schedules FROM Patient p WHERE p.id=:idPatient ORDER BY p.treatment.pump.schedules.startDate DESC ") })
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
    /** Sexo. M si es masculino. F si es femenino. */
    private String sex;
    /** Fecha de nacimiento */
    private Date birthDate;
    /** Usuario que posee ese paciente para ingresar al sistema. */
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_user")
    private User user;
    /** Doctor asociado al paciente. */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;
    /** Tratamiento del paciente. */
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_treatment")
    private Treatment treatment;

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
     * Obtiene el sexo del paciente.
     * 
     * @return el sexo del paciente.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Establece el sexo del paciente.
     * 
     * @param sex
     *            el sexo del paciente.
     */
    public void setSex(String sex) {
        if (!sex.equalsIgnoreCase("M") && !sex.equalsIgnoreCase("F")) {
            throw new RuntimeException(
                    "El sexo es incorrecto. Los valores deben ser 'M' o 'F'");
        }
        this.sex = sex;
    }

    /**
     * Obtiene la fecha de nacimiento del paciente.
     * 
     * @return la fecha de nacimiento del paciente.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Establece la fecha de nacimiento del paceinte.
     * 
     * @param birthDate
     *            la fecha de nacimiento del paciente.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
     * Devuelve el tratamiento del paciente.
     * 
     * @return el tratamiento del paciente.
     */
    public Treatment getTreatment() {
        return treatment;
    }

    /**
     * Establece el tratamiento del paciente.
     * 
     * @param treatment
     *            el tratamiento del paciente.
     */
    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

}
