package com.github.chMatvey.dao

import com.github.chMatvey.dao.impl.DroneDAOFacadeImpl
import com.github.chMatvey.models.Drone

interface DroneDAOFacade {
    suspend fun getDroneById(id: Int): Drone?
    suspend fun getAvailableDrones(): List<Drone>
    suspend fun createDrone(drone: Drone): Drone?
}

val droneDAOFacade = DroneDAOFacadeImpl()