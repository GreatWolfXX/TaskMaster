package com.greatwolf.taskmaster.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Route : NavKey {
    @Serializable
    data object Splash : Route()

    @Serializable
    data object Onboarding : Route()

    @Serializable
    data object SignUp : Route()
}