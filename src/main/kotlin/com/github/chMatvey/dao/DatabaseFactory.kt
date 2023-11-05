package com.github.chMatvey.dao

import kotlinx.coroutines.Dispatchers
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object DatabaseFactory {
    fun init(config: DatabaseConfig) {
        Flyway.configure()
            .dataSource(config.jdbcURL, config.username, config.password)
            .createSchemas(true)
            .schemas(config.schema)
            .defaultSchema(config.schema)
            .load()
            .migrate()

        Database.connect(
            url = "${config.jdbcURL}?currentSchema=${config.schema}",
            driver = config.driverClassName,
            user = config.username,
            password = config.password
        )
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}