package com.greatwolf.database.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.greatwolf.Database
import org.koin.dsl.module

private const val DATABASE_NAME = "task-master-db"

val dataBaseModule = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            Database.Schema,
            get(),
            DATABASE_NAME
        )
    }
}