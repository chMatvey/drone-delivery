package com.github.chMatvey

import com.github.chMatvey.plugins.configureRouting
import com.github.chMatvey.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}
