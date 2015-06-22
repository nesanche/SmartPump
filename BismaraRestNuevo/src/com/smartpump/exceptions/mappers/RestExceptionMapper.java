// Copyright (C) 2015 McAfee, Inc. All rights reserved.
package com.smartpump.exceptions.mappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.smartpump.exceptions.ErrorMessage;
import com.smartpump.exceptions.RestException;

/**
 * Provider that handles the response of a RestException trigger event.
 * Generates the response using the code and the message of this error.
 * 
 */
@Provider
@Component
public class RestExceptionMapper implements ExceptionMapper<RestException> {

    /**
     * Generates the response of the RestException trigger event.
     * 
     * @param ex
     *            the RestException triggered.
     * @return the response in a JSON format using the error code and message.
     */
    public Response toResponse(RestException ex) {
        return Response.status(ex.getCode()).entity(new ErrorMessage(ex))
                .type(MediaType.APPLICATION_JSON).build();
    }
}
