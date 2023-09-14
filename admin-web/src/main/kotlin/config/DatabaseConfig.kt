package com.isyscore.wh.config

import com.isyscore.wh.database.DatabaseConfig
import io.ktor.server.application.*
import org.ktorm.logging.LogLevel

fun Application.loadDatabaseConfig(): DatabaseConfig {
    val cfg = environment.config.config("database").toMap()
    return DatabaseConfig(
        driverClassName = cfg["driverClassName"].toString(),
        url = cfg["url"].toString(),
        username = cfg["username"].toString(),
        password = cfg["password"].toString(),
        initialSize = cfg["initialSize"].toString().toInt(),
        maxActive = cfg["maxActive"].toString().toInt(),
        maxWait = cfg["maxWait"].toString().toLong(),
        logLevel = LogLevel.valueOf(cfg["logLevel"].toString().uppercase())
    )
}