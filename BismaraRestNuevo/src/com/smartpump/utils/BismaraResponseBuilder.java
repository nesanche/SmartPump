package com.smartpump.utils;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 * Clase responsable de construir la respuesta para todos los requests que
 * reciba el servidor.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class BismaraResponseBuilder {

    /** Mapa de cabeceras por defecto que van a tener todas las respuestas. */
    private HashMap<String, String> defaultHeaders;

    /**
     * Constructor por defecto. Establece las cabeceras.
     */
    public BismaraResponseBuilder() {
        defaultHeaders = new HashMap<>();
        defaultHeaders.put("Access-Control-Allow-Origin", "*");
        defaultHeaders.put("Access-Control-Allow-Methods", "GET, POST");
        defaultHeaders.put("Access-Control-Allow-Headers", "*");
    }

    /**
     * Método responsable de construir la respuesta en función de un estado y
     * una entidad que reciba por parámetro. También agrega las cabeceras por
     * defecto seteadas en el atributo.
     * 
     * @param status
     *            el estado de la respuesta.
     * @param entity
     *            la entidad a devolver.
     * @return la respuesta del servidor.
     */
    public Response buildResponse(int status, Object entity) {
        ResponseBuilder builder = Response.status(status);
        builder.entity(entity);
        addDefaultHeaders(builder);
        return builder.build();
    }

    /**
     * Método responsable de construir la respuesta en función de un estado que
     * reciba por parámetro. También agrega las cabeceras por defecto seteadas
     * en el atributo.
     * 
     * @param status
     *            el estado de la respuesta.
     * @return la respuesta del servidor.
     */
    public Response buildResponse(int status) {
        ResponseBuilder builder = Response.status(status);
        addDefaultHeaders(builder);
        return builder.build();
    }

    /**
     * Método privado que agregar a las cabeceras los headers por defecto.
     * 
     * @param builder
     *            el constructor de la respuesta.
     */
    private void addDefaultHeaders(ResponseBuilder builder) {
        for (Entry<String, String> entry : defaultHeaders.entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }
    }

}
