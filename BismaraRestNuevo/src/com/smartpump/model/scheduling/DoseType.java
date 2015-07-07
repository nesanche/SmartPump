package com.smartpump.model.scheduling;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.smartpump.dao.constants.Tables;

/**
 * Entidad que representa el tipo de dosis que se va a dar el paciente. Por
 * ejemplo: insulina basal, bolo de recupero, etc.
 * 
 * @author Franco Ariel Salonia
 *
 */
@Entity
@Table(name = Tables.DOSE_TYPE_TABLE)
@XmlRootElement
public class DoseType {

    /** Id autogenerado del tipo de dosis. */
    @Id
    @GeneratedValue
    private int id;
    /** Descripción del tipo de dosis. */
    private String description;

    /**
     * Devuelve el id del tipo de dosis.
     * 
     * @return el id del tipo de dosis.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id del tipo de dosis.
     * 
     * @param id
     *            el id del tipo de dosis
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve la descripción del tipo de dosis.
     * 
     * @return la descripción del tipo de dosis.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del tipo de dosis.
     * 
     * @param description
     *            la descripción del tipo de dosis.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
