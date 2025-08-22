package com.greatwolf.data.di

import com.greatwolf.common.BuildConfigFieldsProvider
import com.greatwolf.data.repository.AuthRepositoryImpl
import com.greatwolf.data.repository.SettingsRepositoryImpl
import com.greatwolf.datastore.di.dataStoreModule
import com.greatwolf.domain.repository.AuthRepository
import com.greatwolf.domain.repository.SettingsRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import org.koin.dsl.module

val dataModule = module {
    includes(dataStoreModule)

    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
    single<SettingsRepository> {
        SettingsRepositoryImpl(get())
    }

    single<SupabaseClient> {
        val config = get<BuildConfigFieldsProvider>().get()

        createSupabaseClient(
            supabaseUrl = config.supabaseUrl,
            supabaseKey = config.supabaseKey
        ) {
            install(Auth)
        }
    }
}