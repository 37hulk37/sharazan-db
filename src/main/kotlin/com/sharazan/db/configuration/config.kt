package com.sharazan.db.configuration

import com.sharazan.core.AppBuilder
import com.sharazan.core.Lifecycle
import com.sharazan.core.properties.ConfigurationSource
import com.sharazan.db.ExposedDatabase
import org.koin.core.scope.Scope
import org.koin.dsl.bind
import org.koin.dsl.module

fun AppBuilder.database(block: DbProperties.() -> Unit) = registerDatabase {
    DbProperties().apply(block)
}

fun AppBuilder.database() = registerDatabase {
    get<ConfigurationSource>()
        .get<DbProperties>("sharazan.db")
}

private fun AppBuilder.registerDatabase(props: Scope.() -> DbProperties) = apply {
    val dbModule = module {
        single { props() } bind DbProperties::class
        single { ExposedDatabase(get()) } bind Lifecycle::class
    }

    addModule(dbModule)
}
