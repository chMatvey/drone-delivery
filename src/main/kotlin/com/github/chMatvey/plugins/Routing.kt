package com.github.chMatvey.plugins

import com.github.chMatvey.routes.droneRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        droneRouting()
    }
}
