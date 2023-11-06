package com.github.chMatvey

import com.github.chMatvey.dao.DatabaseFactory
import com.github.chMatvey.dao.createDatabaseConfig
import com.github.chMatvey.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureValidation()
    configureStatusPage()
    configureCoin()
    DatabaseFactory.init(createDatabaseConfig(environment.config))
}
