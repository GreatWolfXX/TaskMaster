package com.greatwolf.data.repository

import com.greatwolf.datastore.DataStoreSettingsStore
import com.greatwolf.datastore.PreferencesKey
import com.greatwolf.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    private val dataStoreSettingsStore: DataStoreSettingsStore
) : SettingsRepository {

    override suspend fun setOnboardingState(completed: Boolean) =
        dataStoreSettingsStore.saveState(PreferencesKey.onboardingKey, completed)

    override fun isOnboardingCompleted(): Flow<Boolean> =
        dataStoreSettingsStore.readState(PreferencesKey.onboardingKey, false)
}