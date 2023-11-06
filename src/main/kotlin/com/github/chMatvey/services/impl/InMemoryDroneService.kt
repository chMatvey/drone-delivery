package com.github.chMatvey.services.impl

import com.github.chMatvey.models.Drone
import com.github.chMatvey.models.DroneState
import com.github.chMatvey.services.dto.request.DroneRegisterRequest
import com.github.chMatvey.services.dto.response.DroneRegisterResponse
import com.github.chMatvey.services.DroneService

class InMemoryDroneService : DroneService {
    private val droneStorage = mutableListOf<Drone>()

    override suspend fun getAvailableDrones(): List<Drone> =
        droneStorage.filter { it.state == DroneState.IDLE }

    override suspend fun getDroneById(droneId: Int): Drone? {
        return if (droneId > droneStorage.size) null
        else droneStorage.get(droneId - 1)
    }

    override suspend fun registerDrone(request: DroneRegisterRequest): DroneRegisterResponse {
        val id = droneStorage.size + 1
        Drone(
            id = id,
            serialNumber = request.serialNumber,
            model = request.model,
            weightLimit = request.weightLimit,
            batteryCapacity = 100,
            state = DroneState.IDLE
        )
            .also(droneStorage::add)

        return DroneRegisterResponse(id)
    }
}