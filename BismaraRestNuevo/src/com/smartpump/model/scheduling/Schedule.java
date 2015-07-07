package com.smartpump.model.scheduling;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.smartpump.dao.constants.Tables;

/**
 * Clase que representa la programación de una bomba de insulina en un tiempo
 * determinado.
 * 
 * @author Franco Ariel Salonia
 *
 */
@Entity
@Table(name = Tables.SCHEDULE_TABLE)
@XmlRootElement
public class Schedule {
    /** Id de la programación */
    @Id
    @GeneratedValue
    private int id;
    /** Fecha de comienzo de la programación */
    private Date startDate;
    /** Fecha de finalización de la programación. Si es la actual es null. */
    private Date endDate;
    /** Perfil asociado a la programación */
    private String profile;
    /** Lista de las dosis asociadas a la programación */
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "id_schedule")
    private Set<Dose> doses;
    /** Bandera que establece si la programación ya está confirmada. */
    private boolean confirmed;

    /**
     * Devuelve el id de la programación.
     * 
     * @return el id de la programación.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id de la programación.
     * 
     * @param id
     *            el id de la programación.
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
     * Devuelve la fecha de finalización.
     * 
     * @return la fecha de finalización.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Establece la fecha de finalización.
     * 
     * @param endDate
     *            la fecha de finalización.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Devuelve el perfil de la programación.
     * 
     * @return el perfil de la programación.
     */
    public String getProfile() {
        return profile;
    }

    /**
     * Establece el perfil de la programación.
     * 
     * @param profile
     *            el perfil de la programación.
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    /**
     * Devuelve las dosis asociadas a la programación.
     * 
     * @return las dosis asociadas a la programación.
     */
    public Set<Dose> getDoses() {
        return doses;
    }

    /**
     * Establece las dosis asociadas a la programación.
     * 
     * @param doses
     *            las dosis asociadas a la programación.
     */
    public void setDoses(Set<Dose> doses) {
        this.doses = doses;
    }

    /**
     * Agrega una dosis a la lista de dosis.
     * 
     * @param dose
     *            la dosis a agregar.
     */
    public void addDose(Dose dose) {
        this.doses.add(dose);
    }

    /**
     * Devuelve la bandera que establece si la programación ya está confirmada o
     * no.
     * 
     * @return true si la programación ha sido confirmada, false en caso
     *         contrario.
     */
    public boolean isConfirmed() {
        return confirmed;
    }

    /**
     * Establece la confirmación de la programación.
     * 
     * @param confirmed
     *            la confirmación de la programación.
     */
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

}
