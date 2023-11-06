package com.github.chMatvey.plugins

import com.github.chMatvey.services.dto.request.DroneRegisterRequest
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureValidation() {
    install(RequestValidation) {
        validate<DroneRegisterRequest> { request ->
            val errors = ArrayList<String>()

            if (request.serialNumber.length > 100)
                errors.add("Serial number is too big")

            if (request.weightLimit < 0)
                errors.add("Weight must be postive")
            else if (request.weightLimit > 500)
                errors.add("Weight must be less than 500")

            if (errors.isEmpty())
                ValidationResult.Valid
            else
                ValidationResult.Invalid(errors)
        }
    }
}