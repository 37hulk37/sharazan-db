package com.sharazan.db

import com.sharazan.core.Lifecycle
import com.sharazan.db.configuration.Configuration
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.v1.jdbc.Database
import org.slf4j.LoggerFactory

class ExposedDatabase(
    private val configuration: Configuration,
): Lifecycle {

    private val logger = LoggerFactory.getLogger(ExposedDatabase::class.java)

    private lateinit var dataSource: HikariDataSource

    override fun started() {
        dataSource = HikariDataSource(hikariConfig())

        Database.connect(dataSource)

        logger.info("Database started, connected to ${configuration.url}")
    }

    override fun close() {
        dataSource.close()

        logger.info("Database stopped")
    }

    private fun hikariConfig(): HikariConfig {
        val config = HikariConfig()

        config.jdbcUrl = configuration.url
        config.driverClassName = configuration.driverClassName
        config.username = configuration.username
        config.password = configuration.password
        config.maximumPoolSize = configuration.maxPoolSize

        return config
    }

}
