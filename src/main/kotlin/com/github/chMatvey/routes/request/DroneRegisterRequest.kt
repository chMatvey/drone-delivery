package com.github.chMatvey.routes.request

import com.github.chMatvey.models.DroneModel
import kotlinx.serialization.Serializable

@Serializable
data class DroneRegisterRequest(
    val serialNumber: String,
    val model: DroneModel,
    val weightLimit: Int,
)
