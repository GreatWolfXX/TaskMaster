package com.greatwolf.domain.repository
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun setOnboardingState(completed: Boolean)
    fun isOnboardingCompleted(): Flow<Boolean>
}