package com.smartpump.bismara.requestmanager.model;

import com.google.gson.annotations.Expose;


/**
 * Clase que representa a la entidad de la bomba en el sistema.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class Pump {

    /** Id autogenerado de la bomba. */
    @Expose
    private int id;
    /** Dirección MAC. */
    @Expose
    private String macAddress;
    /** PIN de verificación. */
    @Expose
    private int verificationPin;
    /**
     * Bandera que establece si los bolos programados deben ser automáticos o
     * no.
     */
    @Expose
    private boolean automaticBolus;

    /**
     * Devuelve el id de la bomba.
     * 
     * @return el id de la bomba.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id de la bomba.
     * 
     * @param id
     *            el id de la bomba.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve la dirección MAC de la bomba.
     * 
     * @return la dirección MAC de la bomba.
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * Establece la dirección MAC de la bomba.
     * 
     * @param macAddress
     *            la dirección MAC de la bomba.
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    /**
     * Devuelve el pin de verificación.
     * 
     * @return el pin de verificación.
     */
    public int getVerificationPin() {
        return verificationPin;
    }

    /**
     * Establece el pin de verificación.
     * 
     * @param verificationPin
     *            el pin de verificación.
     */
    public void setVerificationPin(int verificationPin) {
        this.verificationPin = verificationPin;
    }

    /**
     * Devuelve una bandera que establece si el bolo es automático o no.
     * 
     * @return una bandera que establece si el bolo es automático o no.
     */
    public boolean isAutomaticBolus() {
        return automaticBolus;
    }

    /**
     * Establece una bandera que establece si el bolo es automático o no.
     * 
     * @param automaticBolus
     *            una bandera que establece si el bolo es automático o no.
     */
    public void setAutomaticBolus(boolean automaticBolus) {
        this.automaticBolus = automaticBolus;
    }

}
