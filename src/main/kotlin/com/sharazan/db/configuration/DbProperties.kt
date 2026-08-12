package com.sharazan.db.configuration

import kotlinx.serialization.Serializable

@Serializable
data class DbProperties(
    val url: String = "jdbc:postgresql://localhost:5432/postgres",
    val driverClassName: String = "org.postgresql.Driver",
    val username: String = "postgres",
    val password: String = "postgres",
    val maxPoolSize: Int = 10,
)