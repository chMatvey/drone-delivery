package com.github.chMatvey.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Drone(
    val id: Int? = null,
    val serialNumber: String,
    val model: DroneModel,
    val weightLimit: Int,
    val batteryCapacity: Int,
    val state: DroneState
)

object Drones : Table() {
    val id = integer("id").autoIncrement("drones_id_seq")
    val serialNumber = varchar("serial_number", 100)
    val model = varchar("model", 20)
    val weightLimit = integer("weight_limit")
    val batteryCapacity = integer("battery_capacity")
    val state = varchar("state", 20)
}