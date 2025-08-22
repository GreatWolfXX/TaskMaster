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

    @Serializable
    data object SignIn : Route()

    @Serializable
    data class Verification(val email: String) : Route()

    @Serializable
    data class Success(
        val title: String,
        val desc: String,
        val btnText: String
    ) : Route()
}