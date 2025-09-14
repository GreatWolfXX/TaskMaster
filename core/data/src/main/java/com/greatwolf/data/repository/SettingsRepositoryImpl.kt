package com.greatwolf.data.repository

import com.greatwolf.datastore.DataStoreSettings
import com.greatwolf.datastore.PreferencesKey
import com.greatwolf.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    private val DataStoreSettings: DataStoreSettings
) : SettingsRepository {

    override suspend fun setOnboardingState(completed: Boolean) =
        DataStoreSettings.saveState(PreferencesKey.onboardingKey, completed)

    override fun isOnboardingCompleted(): Flow<Boolean> =
        DataStoreSettings.readState(PreferencesKey.onboardingKey, false)

    override suspend fun setRememberSessionState(remember: Boolean) =
        DataStoreSettings.saveState(PreferencesKey.rememberSession, remember)

    override fun isRememberSession(): Flow<Boolean> =
        DataStoreSettings.readState(PreferencesKey.rememberSession, false)
}