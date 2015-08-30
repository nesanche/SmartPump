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
    /** Tel�fono de contacto */
    @Expose
    private String phone;
    /** Email de contacto */
    @Expose
    private String email;
    /** N�mero de matr�cula. Debe ser �nico. */
    @Expose
    private String registrationNumber;
    /** Direcci�n donde trabaja el m�dico. */
    @Expose
    private String address;
    /** Usuario que posee ese doctor para ingresar al sistema. */
    @Expose
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
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Establece la matr�cula del doctor.
     * 
     * @param registrationNumber
     *            la matr�cula del doctor.
     */
    public void setRegistrationNumber(String registrationNumber) {
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
