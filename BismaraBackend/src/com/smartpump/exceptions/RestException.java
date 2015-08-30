// Copyright (C) 2015 McAfee, Inc. All rights reserved.
package com.smartpump.exceptions;

import javax.ws.rs.core.Response;

/**
 * Class responsible for mapping application related exceptions. Any exception
 * not contemplated in the API should extend this class.
 */
public class RestException extends RuntimeException {

    /** Serial Version UID */
    private static final long serialVersionUID = -8999932578270387947L;

    /** An application error specific code. */
    private int code;

    /**
     * Constructor that instantiates the exception and sets the code and the
     * message.
     * 
     * @param code
     *            the code of the exception
     * @param message
     *            the message of the exception
     */
    public RestException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Default constructor. Sets a default code and message.
     */
    public RestException() {
        super("Internal server error.");
        this.code = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
    }

    /**
     * Gets the code of the exception.
     * 
     * @return the code of the exception.
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the code of the exception.
     * 
     * @param code
     *            the code of the exception.
     */
    public void setCode(int code) {
        this.code = code;
    }

}
