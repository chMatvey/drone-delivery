package com.github.chMatvey.services

import com.github.chMatvey.models.Drone
import com.github.chMatvey.routes.request.DroneRegisterRequest
import com.github.chMatvey.services.impl.InMemoryDroneService

interface DroneService {
    suspend fun getAvailableDrones(): List<Drone>

    suspend fun registerDrone(request: DroneRegisterRequest)
}

val droneService = InMemoryDroneService()