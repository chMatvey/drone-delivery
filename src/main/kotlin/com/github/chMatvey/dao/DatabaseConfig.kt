package com.github.chMatvey.dao

import io.ktor.server.config.*

data class DatabaseConfig(
    val driverClassName: String,
    val jdbcURL: String,
    val username: String,
    val password: String,
    val schema: String
)

fun createDatabaseConfig(config: ApplicationConfig): DatabaseConfig = DatabaseConfig(
    driverClassName = config.property("database.driverClassName").getString(),
    jdbcURL = config.property("database.jdbcUrl").getString(),
    username = config.property("database.username").getString(),
    password = config.property("database.password").getString(),
    schema = config.property("database.schema").getString()
)