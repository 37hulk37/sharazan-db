package com.sharazan.db.configuration

import com.sharazan.core.AppBuilder
import com.sharazan.core.properties.ConfigurationSource
import com.sharazan.db.ExposedDatabase

fun AppBuilder.database() = apply {
    val configuration = get<ConfigurationSource>()
        ?.get<Configuration>("sharazan.db.")
        ?: Configuration()

    install(ExposedDatabase(configuration))
}
