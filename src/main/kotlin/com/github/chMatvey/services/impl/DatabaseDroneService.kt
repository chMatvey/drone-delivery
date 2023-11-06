package com.github.chMatvey.services.impl

import com.github.chMatvey.dao.droneDAOFacade
import com.github.chMatvey.models.Drone
import com.github.chMatvey.models.DroneState.IDLE
import com.github.chMatvey.services.dto.request.DroneRegisterRequest
import com.github.chMatvey.services.dto.response.DroneRegisterResponse
import com.github.chMatvey.services.DroneService

class DatabaseDroneService : DroneService {
    override suspend fun getAvailableDrones(): List<Drone> = droneDAOFacade.getAvailableDrones()

    override suspend fun getDroneById(droneId: Int): Drone? = droneDAOFacade.getDroneById(droneId)

    override suspend fun registerDrone(request: DroneRegisterRequest): DroneRegisterResponse {
        val drone = droneDAOFacade.createDrone(
            Drone(
                serialNumber = request.serialNumber,
                model = request.model,
                weightLimit = request.weightLimit,
                batteryCapacity = 100,
                state = IDLE
            )
        )
        return DroneRegisterResponse(drone?.id!!)
    }
}