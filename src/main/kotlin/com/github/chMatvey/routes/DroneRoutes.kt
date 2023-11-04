package com.github.chMatvey.routes

import com.github.chMatvey.models.Drone
import com.github.chMatvey.models.DroneState.IDLE
import com.github.chMatvey.models.droneStorage
import com.github.chMatvey.routes.request.DroneRegistration
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.droneRouting() {
    route("/api/v1/drones") {
        get("/available") {
            call.respond(OK, droneStorage.filter { it.state == IDLE })
        }
        post("/register") {
            val request = call.receive<DroneRegistration>()

            Drone(
                serialNumber = request.serialNumber,
                model = request.model,
                weightLimit = request.weightLimit,
                batteryCapacity = 100,
                state = IDLE
            )
                .also(droneStorage::add)

            call.respond(Created)
        }
    }
}