package com.github.chMatvey.services.impl

import com.github.chMatvey.models.Drone
import com.github.chMatvey.models.DroneState
import com.github.chMatvey.routes.request.DroneRegisterRequest
import com.github.chMatvey.services.DroneService

class InMemoryDroneService : DroneService {
    private val droneStorage = mutableListOf<Drone>()

    override suspend fun getAvailableDrones(): List<Drone> =
        droneStorage.filter { it.state == DroneState.IDLE }

    override suspend fun registerDrone(request: DroneRegisterRequest) {
        Drone(
            serialNumber = request.serialNumber,
            model = request.model,
            weightLimit = request.weightLimit,
            batteryCapacity = 100,
            state = DroneState.IDLE
        )
            .also(droneStorage::add)
    }
}