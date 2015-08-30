package com.smartpump.model.scheduling;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

/**
 * Clase que representa la programaci�n de una bomba de insulina en un tiempo
 * determinado.
 * 
 * @author Franco Ariel Salonia
 *
 */
@Entity
@Table(name = Tables.SCHEDULE_TABLE)
@NamedQueries({ @NamedQuery(name = Queries.PATIENT_GET_SCHEDULES, query = "SELECT s FROM Schedule s, Patient p WHERE p.id = :idPatient AND p.treatment.pump.id = s.pump.id") })
@XmlRootElement
public class Schedule {
    /** Id de la programaci�n */
    @Id
    @GeneratedValue
    private int id;
    /** Fecha de comienzo de la programaci�n */
    private Date startDate;
    /** Fecha de finalizaci�n de la programaci�n. Si es la actual es null. */
    private Date endDate;
    /** Perfil asociado a la programaci�n */
    private String profile;
    /** Bandera que establece si la programaci�n ya est� confirmada. */
    private boolean confirmed;
    @ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pump")
    private Pump pump;

    /**
     * Devuelve el id de la programaci�n.
     * 
     * @return el id de la programaci�n.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id de la programaci�n.
     * 
     * @param id
     *            el id de la programaci�n.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve la fecha de inicio.
     * 
     * @return la fecha de inicio.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Establece la fecha de inicio.
     * 
     * @param startDate
     *            la fecha de inicio.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Devuelve la fecha de finalizaci�n.
     * 
     * @return la fecha de finalizaci�n.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Establece la fecha de finalizaci�n.
     * 
     * @param endDate
     *            la fecha de finalizaci�n.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Devuelve el perfil de la programaci�n.
     * 
     * @return el perfil de la programaci�n.
     */
    public String getProfile() {
        return profile;
    }

    /**
     * Establece el perfil de la programaci�n.
     * 
     * @param profile
     *            el perfil de la programaci�n.
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    /**
     * Devuelve la bandera que establece si la programaci�n ya est� confirmada o
     * no.
     * 
     * @return true si la programaci�n ha sido confirmada, false en caso
     *         contrario.
     */
    public boolean isConfirmed() {
        return confirmed;
    }

    /**
     * Establece la confirmaci�n de la programaci�n.
     * 
     * @param confirmed
     *            la confirmaci�n de la programaci�n.
     */
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Pump getPump() {
        return pump;
    }

    public void setPump(Pump pump) {
        this.pump = pump;
    }

}