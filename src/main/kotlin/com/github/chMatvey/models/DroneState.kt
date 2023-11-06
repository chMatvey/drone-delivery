package com.github.chMatvey.models

enum class DroneState {
    IDLE,
    LOADING,
    LOADED,
    DELIVERING,
    DELIVERED,
    RETURNING
}

fun toDroneState(value: String): DroneState {
    return DroneState.entries.firstOrNull { it.name == value }
        ?: throw IllegalArgumentException("Unknown drone state: $value")
}