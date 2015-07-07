package com.smartpump.model.scheduling;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.smartpump.dao.constants.Tables;

/**
 * Entidad que representa el tratamiento de un paciente relacionado a la bomba.
 * 
 * @author Franco Ariel Salonia
 *
 */
@Entity
@Table(name = Tables.TREATMENT_TABLE)
@XmlRootElement
public class Treatment {
    /** Id del tratamiento */
    @Id
    @GeneratedValue
    private int id;

    /** Bomba asociada al tratamiento. */
    @OneToOne
    @JoinColumn(name = "id_pump")
    private Pump pump;

    /**
     * Devuelve el id del tratamiento
     * 
     * @return el id del tratamiento
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id del tratamiento
     * 
     * @param id
     *            el id del tratamiento
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve la bomba asociada al tratamiento.
     * 
     * @return la bomba asociada al tratamiento.
     */
    public Pump getPump() {
        return pump;
    }

    /**
     * Establece las programaciones del tratamiento.
     * 
     * @param schedules
     *            las programaciones del tratamiento.
     */
    public void setPump(Pump pump) {
        this.pump = pump;
    }

}
