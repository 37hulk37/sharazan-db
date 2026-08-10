package com.sharazan.db

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.v1.jdbc.JdbcTransaction
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

suspend fun <T> query(statement: JdbcTransaction.() -> T): T =
    withContext(Dispatchers.IO) {
        transaction(statement = statement)
    }