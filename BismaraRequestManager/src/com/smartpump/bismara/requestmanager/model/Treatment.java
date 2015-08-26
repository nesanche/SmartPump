package com.smartpump.bismara.requestmanager.model;

import com.google.gson.annotations.Expose;

/**
* Entidad que representa el tratamiento de un paciente relacionado a la bomba.
* 
* @author Franco Ariel Salonia
*
*/
public class Treatment {
   /** Id del tratamiento */
   private int id;

   /** Bomba asociada al tratamiento. */
   @Expose 
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
