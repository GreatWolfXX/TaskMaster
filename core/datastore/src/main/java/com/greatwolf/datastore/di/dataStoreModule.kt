package com.greatwolf.datastore.di

import com.greatwolf.datastore.DataStoreSettings
import org.koin.dsl.module

val dataStoreModule = module {
    single<DataStoreSettings> {
        DataStoreSettings(get())
    }
}