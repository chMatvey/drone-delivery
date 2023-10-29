package com.github.chMatvey;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/hello")
public class GreetingResource {
    @Inject
    GreetingService service;

    @GET
    @Produces(TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/greeting/{name}")
    @Produces(TEXT_PLAIN)
    public String greeting(String name) {
        return service.greeting(name);
    }
}
