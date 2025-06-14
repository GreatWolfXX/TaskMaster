package com.greatwolf.taskmaster.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.greatwolf.onboarding.OnboardingScreen
import com.greatwolf.taskmaster.SplashScreen

@Composable
fun BasicNavigation() {
    val backStack = rememberNavBackStack<Route>(Route.Splash)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Route.Splash> {
                SplashScreen(
                    navigate = {
                        backStack.add(Route.Onboarding)
                        backStack.remove(Route.Splash)
                    }
                )
            }

            entry<Route.Onboarding> {
                OnboardingScreen()
            }
        }
    )
}