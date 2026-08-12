package com.sharazan.db

import com.sharazan.core.Lifecycle
import com.sharazan.db.configuration.DbProperties
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.v1.jdbc.Database
import org.slf4j.LoggerFactory
import java.io.Closeable

class ExposedDatabase(
    private val properties: DbProperties,
): Lifecycle, Closeable {

    private val logger = LoggerFactory.getLogger(ExposedDatabase::class.java)

    private lateinit var dataSource: HikariDataSource

    override fun onStart() {
        dataSource = HikariDataSource(hikariConfig())

        Database.connect(dataSource)

        logger.info("Database started, connected to ${properties.url}")
    }

    override fun onStop() {
        close()
    }

    override fun close() {
        dataSource.close()

        logger.info("Database stopped")
    }

    private fun hikariConfig(): HikariConfig {
        val config = HikariConfig()

        config.jdbcUrl = properties.url
        config.driverClassName = properties.driverClassName
        config.username = properties.username
        config.password = properties.password
        config.maximumPoolSize = properties.maxPoolSize

        return config
    }

}
