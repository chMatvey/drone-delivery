package com.github.chMatvey.routes

import com.github.chMatvey.services.droneService
import com.github.chMatvey.services.dto.request.DroneRegisterRequest
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.droneRouting() {
    route("/api/v1/drones") {
        get("/available") {
            call.respond(droneService.getAvailableDrones())
        }
        get("/{droneId}") {
            val droneId = call.parameters.getOrFail("droneId").toInt()
            val drone = droneService.getDroneById(droneId) ?: return@get call.respondText(
                "No drone with id $droneId", status = NotFound
            )

            call.respond(drone)

        }
        post("/register") {
            val request = call.receive<DroneRegisterRequest>()
            val response = droneService.registerDrone(request)

            call.respond(Created, response)
        }
    }
}