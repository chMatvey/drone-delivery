package com.github.chMatvey.services

import com.github.chMatvey.models.Drone
import com.github.chMatvey.services.dto.request.DroneRegisterRequest
import com.github.chMatvey.services.dto.response.DroneRegisterResponse

interface DroneService {
    suspend fun getAvailableDrones(): List<Drone>

    suspend fun getDroneById(droneId: Int): Drone?

    suspend fun registerDrone(request: DroneRegisterRequest): DroneRegisterResponse
}