package com.github.chMatvey.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class DroneModel( val value: String) {
    @SerialName("Lightweight") LIGHTWEIGHT("Lightweight"),
    @SerialName("Middleweight") MIDDLEWEIGHT("Middleweight"),
    @SerialName("Cruiserweight") CRUISERWEIGHT("Cruiserweight"),
    @SerialName("Heavyweight") HEAVYWEIGHT("Heavyweight")
}