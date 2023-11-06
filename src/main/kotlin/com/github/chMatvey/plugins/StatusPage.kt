package com.github.chMatvey.plugins

import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPage() {
    install(StatusPages) {
        exception<RequestValidationException> { call, exception ->
            call.respond(BadRequest, exception.reasons.joinToString())
        }
    }
}