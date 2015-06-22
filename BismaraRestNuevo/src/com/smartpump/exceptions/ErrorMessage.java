// Copyright (C) 2015 McAfee, Inc. All rights reserved.
package com.smartpump.exceptions;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.BeanUtils;

/**
 * Class used for exception mappers to create the error message retrieved in
 * response of an exception event.
 *
 */
@XmlRootElement
public class ErrorMessage {
    /** Represents an application specific error code. */
    @XmlElement(name = "code")
    private int code;

    /** Represents a message that describes the error. */
    @XmlElement(name = "message")
    private String message;

    /**
     * Gets the code of the error.
     * 
     * @return the code of the error.
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the code of the error.
     * 
     * @param code
     *            the code of the error.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets the message that describes the error.
     * 
     * @return the message that describes the error.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message that describes the error.
     * 
     * @param message
     *            the message that describes the error.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Constructor used for own RestException.
     * 
     * @param ex
     *            the RestException to be printed.
     */
    public ErrorMessage(RestException ex) {
        try {
            BeanUtils.copyProperties(this, ex);
        } catch (Exception e) {
        }
    }

    /**
     * Constructor that receives the exception information directly.
     * 
     * @param statusCode
     *            the code of the exception.
     * @param message
     *            a message that describes the exception.
     */
    public ErrorMessage(int statusCode, String message) {
        this.code = statusCode;
        this.message = message;
    }

    /**
     * Constructor used for 404 Not Found Error.
     * 
     * @param ex
     *            the Exception triggered by this error.
     */
    public ErrorMessage(javax.ws.rs.NotFoundException ex) {
        this(Response.Status.NOT_FOUND.getStatusCode(), "Not found.");
    }

    /**
     * Constructor used for 401 unauthorized error.
     * 
     * @param ex
     *            the Exception triggered by this error.
     */
    public ErrorMessage(javax.ws.rs.NotAuthorizedException ex) {
        this(Response.Status.UNAUTHORIZED.getStatusCode(),
                "Unauthorized. Invalid access token.");
    }

    /**
     * Constructor used for 400 Bad Request error.
     * 
     * @param ex
     *            the Exception triggered by this error.
     * @param message
     *            the cause of the Bad exception error.
     */
    public ErrorMessage(javax.ws.rs.BadRequestException ex, String message) {
        this(Response.Status.BAD_REQUEST.getStatusCode(), message);
    }

    /**
     * Generic constructor that receives a Throwable object.
     * 
     * @param e
     *            the Throwable object.
     */
    public ErrorMessage(Throwable e) {
        this(500, e.getMessage());
    }
}
