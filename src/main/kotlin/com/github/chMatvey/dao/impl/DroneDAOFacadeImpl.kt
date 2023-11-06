package com.github.chMatvey.dao.impl

import com.github.chMatvey.dao.DatabaseFactory.dbQuery
import com.github.chMatvey.dao.DroneDAOFacade
import com.github.chMatvey.models.Drone
import com.github.chMatvey.models.DroneState.IDLE
import com.github.chMatvey.models.Drones
import com.github.chMatvey.models.toDroneModel
import com.github.chMatvey.models.toDroneState
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class DroneDAOFacadeImpl : DroneDAOFacade {
    override suspend fun getDroneById(id: Int): Drone? = dbQuery {
        Drones
            .select { Drones.id eq id }
            .map(::resultRowToDrone)
            .firstOrNull()
    }

    override suspend fun getAvailableDrones(): List<Drone> = dbQuery {
        Drones
            .select { Drones.state eq IDLE.name }
            .map(::resultRowToDrone)
    }

    override suspend fun createDrone(drone: Drone) = dbQuery {
        val insertStatement = Drones.insert {
            it[serialNumber] = drone.serialNumber
            it[model] = drone.model.value
            it[weightLimit] = drone.weightLimit
            it[batteryCapacity] = drone.batteryCapacity
            it[state] = drone.state.name
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToDrone)
    }

    private fun resultRowToDrone(row: ResultRow): Drone = Drone(
        id = row[Drones.id],
        serialNumber = row[Drones.serialNumber],
        model = toDroneModel(row[Drones.model]),
        weightLimit = row[Drones.weightLimit],
        batteryCapacity = row[Drones.batteryCapacity],
        state = toDroneState(row[Drones.state])
    )
}