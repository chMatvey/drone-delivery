package com.github.chMatvey.routes

import com.github.chMatvey.routes.request.DroneRegisterRequest
import com.github.chMatvey.services.droneService
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.droneRouting() {
    route("/api/v1/drones") {
        get("/available") {
            call.respond(droneService.getAvailableDrones())
        }
        post("/register") {
            val request = call.receive<DroneRegisterRequest>()
            droneService.registerDrone(request)
            call.respond(Created)
        }
    }
}