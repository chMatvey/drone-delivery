package com.github.chMatvey.web;

import com.github.chMatvey.service.DroneService;
import com.github.chMatvey.web.request.DroneRegisterRequest;
import com.github.chMatvey.web.response.DroneRegisterResponse;
import com.github.chMatvey.web.response.DroneResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

@Path("/api/v1/drones")
public class DroneResource {
    @Inject
    DroneService droneService;

    @GET
    @Path("/available")
    public List<DroneResponse> availableDrones() {
        return droneService.availableDrones();
    }

    @POST
    @Path("/register")
    @ResponseStatus(201)
    public DroneRegisterResponse registerDrone(DroneRegisterRequest request) {
        return droneService.registerDrone(request);
    }
}
