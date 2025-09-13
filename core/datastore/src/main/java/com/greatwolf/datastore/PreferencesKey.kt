package com.greatwolf.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferencesKey {
    val onboardingKey = booleanPreferencesKey(name = "onboarding_completed")
    val rememberSession = booleanPreferencesKey(name = "is_remember_session")
}