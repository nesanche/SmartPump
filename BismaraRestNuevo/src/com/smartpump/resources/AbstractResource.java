package com.smartpump.resources;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.smartpump.security.ResourceFilter;
import com.smartpump.utils.BismaraResponseBuilder;

public class AbstractResource {

    @Autowired
    protected Gson gson;
    @Autowired
    protected BismaraResponseBuilder responseBuilder;
    @Autowired
    protected ResourceFilter resourceFilter;

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public void setResponseBuilder(BismaraResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

    public void setResourceFilter(ResourceFilter resourceFilter) {
        this.resourceFilter = resourceFilter;
    }
}
