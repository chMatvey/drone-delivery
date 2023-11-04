package com.github.chMatvey.models

import kotlinx.serialization.Serializable

@Serializable
data class Drone(
    val serialNumber: String,
    val model: DroneModel,
    val weightLimit: Int,
    val batteryCapacity: Int,
    val state: DroneState
)
