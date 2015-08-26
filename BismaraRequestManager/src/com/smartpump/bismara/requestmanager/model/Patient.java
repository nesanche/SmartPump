package com.smartpump.bismara.requestmanager.model;

import com.google.gson.annotations.Expose;

public class Patient {

    /** Id autogenerado */
    private int id;
    /** Nombre de pila */
    @Expose
    private String firstName;
    /** Apellido */
    @Expose
    private String lastName;
    /** Teléfono principal de contacto */
    @Expose
    private String phoneOne;
    @Expose
    /** Teléfono secundario de contacto */
    private String phoneTwo;
    @Expose
    /** Email */
    private String email;
    @Expose
    /** Dirección de residencia */
    private String address;
    @Expose
    /** Sexo. M si es masculino. F si es femenino. */    
    private String sex;
    /** Fecha de nacimiento */
    @Expose
    private String birthDate;
    @Expose
    /** Usuario que posee ese paciente para ingresar al sistema. */
    private User user;
    @Expose
    /** Doctor asociado al paciente. */
    private Doctor doctor;
    @Expose
    /** Tratamiento del paciente. */
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
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Establece la fecha de nacimiento del paceinte.
     * 
     * @param birthDate
     *            la fecha de nacimiento del paciente.
     */
    public void setBirthDate(String birthDate) {
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
