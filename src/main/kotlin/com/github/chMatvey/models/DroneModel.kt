package com.github.chMatvey.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class DroneModel(val value: String) {
    @SerialName("Lightweight")
    LIGHTWEIGHT("Lightweight"),

    @SerialName("Middleweight")
    MIDDLEWEIGHT("Middleweight"),

    @SerialName("Cruiserweight")
    CRUISERWEIGHT("Cruiserweight"),

    @SerialName("Heavyweight")
    HEAVYWEIGHT("Heavyweight")
}

fun toDroneModel(value: String): DroneModel {
    return DroneModel.entries.firstOrNull { it.value == value }
        ?: throw IllegalArgumentException("Unknown drone model: $value")
}