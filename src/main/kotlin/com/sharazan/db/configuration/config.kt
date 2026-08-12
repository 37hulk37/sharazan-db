package com.sharazan.db.configuration

import com.sharazan.core.AppBuilder
import com.sharazan.core.properties.ConfigurationSource
import com.sharazan.db.ExposedDatabase
import org.koin.dsl.bind
import org.koin.dsl.module

fun AppBuilder.database(block: DbProperties.() -> Unit) = apply {
    val props = DbProperties().apply(block)

    val dbModule = module {
        single { props } bind DbProperties::class
        single { ExposedDatabase(props) }
    }

    addModule(dbModule)
}

fun AppBuilder.database() = apply {


    val dbModule = module {
        single {
            val source = get<ConfigurationSource>()
            val props = source.get<DbProperties>("sharazan.db")

            props
        }
        single { ExposedDatabase(get()) }
    }

    addModule(dbModule)
}
