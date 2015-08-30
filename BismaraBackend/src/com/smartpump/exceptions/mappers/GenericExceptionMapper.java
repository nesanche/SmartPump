// Copyright (C) 2015 McAfee, Inc. All rights reserved.
package com.smartpump.exceptions.mappers;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.smartpump.exceptions.ErrorMessage;
import com.smartpump.utils.BismaraResponseBuilder;
import com.sun.jersey.api.NotFoundException;

/**
 * Provider that handles the response of a generic exception trigger event.
 * Generates the response using the code and the message of this error.
 * 
 */
@Provider
@Component
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    /** Atributo encargado de la construcción de las response. */
    @Autowired
    private BismaraResponseBuilder responseBuilder;

    /**
     * Overridden method from ExceptionMapper. Receives a generic exception and
     * generates the JSON response using the status code and the correct
     * message.
     * 
     * @param ex
     *            a generic exception.
     * @return the response in a JSON format with the error code and message.
     */
    @Override
    public Response toResponse(Throwable ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex);
        setHttpStatus(ex, errorMessage);
        setMessage(ex, errorMessage);
        Gson gson = new Gson();
        String json = gson.toJson(errorMessage);
        return responseBuilder.buildResponse(errorMessage.getCode(), json,
                MediaType.APPLICATION_JSON);
    }

    /**
     * Sets the message of the response according to the type of exception.
     * 
     * @param ex
     *            the exception handled.
     * @param errorMessage
     *            the ErrorMessage that it is being generated.
     */
    private void setMessage(Throwable ex, ErrorMessage errorMessage) {
        if (ex instanceof NotFoundException) {
            errorMessage.setMessage("HTTP Not Found.");
        } else if (ex instanceof com.sun.jersey.api.ConflictException) {
            errorMessage.setMessage("Unauthorized. Invalid access token.");
        } else {
            errorMessage.setMessage(ex.getMessage());
        }
    }

    /**
     * Sets the code of the response according to the type of exception.
     * 
     * @param ex
     *            the exception handled.
     * @param errorMessage
     *            the ErrorMessage that it is being generated.
     */
    private void setHttpStatus(Throwable ex, ErrorMessage errorMessage) {
        if (ex instanceof WebApplicationException) {
            errorMessage.setCode(((WebApplicationException) ex).getResponse()
                    .getStatus());
        } else {
            errorMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR
                    .getStatusCode());
        }
    }

}
