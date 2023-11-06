package com.github.chMatvey

import com.github.chMatvey.dao.DatabaseFactory
import com.github.chMatvey.dao.createDatabaseConfig
import com.github.chMatvey.plugins.configureRouting
import com.github.chMatvey.plugins.configureSerialization
import com.github.chMatvey.plugins.configureStatusPage
import com.github.chMatvey.plugins.configureValidation
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureValidation()
    configureStatusPage()
    DatabaseFactory.init(createDatabaseConfig(environment.config))
}
