package com.greatwolf.data.di

import com.greatwolf.data.repository.SettingsRepositoryImpl
import com.greatwolf.datastore.di.dataStoreModule
import com.greatwolf.domain.repository.SettingsRepository
import org.koin.dsl.module

val dataModule = module {
    includes(dataStoreModule)
    single<SettingsRepository> {
        SettingsRepositoryImpl(get())
    }
}