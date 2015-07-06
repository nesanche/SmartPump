// Copyright (C) 2015 McAfee, Inc. All rights reserved.
package com.smartpump.exceptions.mappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartpump.exceptions.ErrorMessage;
import com.smartpump.exceptions.RestException;
import com.smartpump.utils.BismaraResponseBuilder;

/**
 * Provider that handles the response of a RestException trigger event.
 * Generates the response using the code and the message of this error.
 * 
 */
@Provider
@Component
public class RestExceptionMapper implements ExceptionMapper<RestException> {

    /** Atributo encargado de la construcción de las response. */
    @Autowired
    private BismaraResponseBuilder responseBuilder;

    /**
     * Generates the response of the RestException trigger event.
     * 
     * @param ex
     *            the RestException triggered.
     * @return the response in a JSON format using the error code and message.
     */
    public Response toResponse(RestException ex) {
        return responseBuilder.buildResponse(ex.getCode(),
                new ErrorMessage(ex), MediaType.APPLICATION_JSON);
    }
}
