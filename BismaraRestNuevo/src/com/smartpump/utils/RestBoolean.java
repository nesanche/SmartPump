package com.smartpump.utils;

import java.io.Serializable;

public class RestBoolean implements Serializable {

    /** Constante para la interfaz Serializable */
    private static final long serialVersionUID = 17567456L;

    private Boolean value;

    public RestBoolean() {

    }

    public RestBoolean(Boolean value) {
        this.value = value;
    }

    public Boolean isValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
