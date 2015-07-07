package com.smartpump.model.scheduling;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.smartpump.dao.constants.Tables;

/**
 * Entidad que representa una dosis en particular de la bomba.
 * 
 * @author Franco Ariel Salonia
 *
 */
@Entity
@Table(name = Tables.DOSE_TABLE)
@XmlRootElement
public class Dose {

    /** Id de la dosis. */
    @Id
    @GeneratedValue
    private int id;
    /** Cantidad de unidades de insulina. */
    private float units;
    /** La hora en que la dosis será aplicada. */
    private Date time;
    /** El día de la semana donde se aplicará. */
    private int day;
    /** Tipo de dosis. */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_dose_type")
    private DoseType doseType;

    /**
     * Devuelve el id de la dosis.
     * 
     * @return el id de la dosis.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id de la dosis.
     * 
     * @param id
     *            el id de la dosis.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve la cantidad de unidades de insulina.
     * 
     * @return la cantidad de unidades de insulina.
     */
    public float getUnits() {
        return units;
    }

    /**
     * Establece la cantidad de unidades de insulina.
     * 
     * @param units
     *            la cantidad de unidades de insulina.
     */
    public void setUnits(float units) {
        this.units = units;
    }

    /**
     * Devuelve la hora en que la dosis será aplicada.
     * 
     * @return la hora en que la dosis será aplicada.
     */
    public Date getTime() {
        return time;
    }

    /**
     * Establece la hora en que la dosis será aplicada.
     * 
     * @param time
     *            la hora en que la dosis será aplicada.
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Devuelve el día de la semana de la dosis.
     * 
     * @return el día de la semana de la dosis.
     */
    public int getDay() {
        return day;
    }

    /**
     * Establece el día de la semana de la dosis.
     * 
     * @param day
     *            el día de la semana de la dosis.
     */
    public void setDay(int day) {
        if (day < 1 || day > 7)
            throw new RuntimeException(
                    "El formato de días es incorrecto. Colocar un día entre 1 y 7");
        this.day = day;
    }

    /**
     * Devuelve el tipo de dosis.
     * 
     * @return el tipo de dosis.
     */
    public DoseType getDoseType() {
        return doseType;
    }

    /**
     * Establece el tipo de dosis.
     * 
     * @param doseType
     *            el tipo de dosis.
     */
    public void setDoseType(DoseType doseType) {
        this.doseType = doseType;
    }

}
