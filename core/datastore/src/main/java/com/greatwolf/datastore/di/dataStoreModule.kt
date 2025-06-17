package com.greatwolf.datastore.di

import com.greatwolf.datastore.DataStoreSettingsStore
import org.koin.dsl.module

val dataStoreModule = module {
    single<DataStoreSettingsStore> {
        DataStoreSettingsStore(get())
    }
}