package com.smartpump.model.scheduling;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.smartpump.dao.constants.Queries;
import com.smartpump.dao.constants.Tables;

/**
 * Clase que representa a la entidad de la bomba en el sistema.
 * 
 * @author Franco Ariel Salonia
 *
 */
@Entity
@Table(name = Tables.PUMP_TABLE)
@NamedQueries({ @NamedQuery(name = Queries.PUMP_GET_BY_MAC_ADDRESS, query = "SELECT p FROM Pump p WHERE p.macAddress=:macAddress") })
@XmlRootElement
public class Pump {

    /** Id autogenerado de la bomba. */
    @Id
    @GeneratedValue
    private int id;
    /** Dirección MAC. */
    private String macAddress;
    /** PIN de verificación. */
    private int verificationPin;
    /**
     * Bandera que establece si los bolos programados deben ser automáticos o
     * no.
     */
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
