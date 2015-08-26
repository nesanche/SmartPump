package com.smartpump.bismara.requestmanager.model;

import com.google.gson.annotations.Expose;


/**
 * Entidad que representa a un doctor registrado en el sistema.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class Doctor {

    
    /** Id autogenerado del doctor. */
    @Expose
    private int id;
    /** Nombre/s de pila */
    @Expose
    private String firstName;
    /** Apellido */
    @Expose
    private String lastName;
    /** Teléfono de contacto */
    @Expose
    private String phone;
    /** Email de contacto */
    @Expose
    private String email;
    /** Número de matrícula. Debe ser único. */
    @Expose
    private String registrationNumber;
    /** Dirección donde trabaja el médico. */
    @Expose
    private String address;
    /** Usuario que posee ese doctor para ingresar al sistema. */
    @Expose
    private User user;

    /**
     * Devuelve el id único del doctor.
     * 
     * @return el id único del doctor.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id único del doctor.
     * 
     * @param id
     *            el id único del doctor.
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
     * Devuelve el teléfono de contacto del doctor.
     * 
     * @return el teléfono de contacto del doctor.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Establece el teléfono de contacto del doctor.
     * 
     * @param phone
     *            el teléfono de contacto del doctor.
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
     * Devuelve la matrícula del doctor.
     * 
     * @return la matrícula del doctor.
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Establece la matrícula del doctor.
     * 
     * @param registrationNumber
     *            la matrícula del doctor.
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Devuelve la dirección donde trabaja el médico.
     * 
     * @return la dirección donde trabaja el médico.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Establece la dirección donde trabaja el médico.
     * 
     * @param address
     *            la dirección donde trabaja el médico.
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
