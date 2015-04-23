package com.smartpump.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/bismara")
public class TestJersey {

	@GET
    @Path("/test")
    public String testMethod() {
        return "this is a test";
    }
}
