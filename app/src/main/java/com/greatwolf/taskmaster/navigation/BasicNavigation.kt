package com.greatwolf.taskmaster.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.greatwolf.auth.create.SignUpScreen
import com.greatwolf.auth.verification.VerificationScreen
import com.greatwolf.onboarding.OnboardingScreen
import com.greatwolf.taskmaster.SplashScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun BasicNavigation() {
    val backStack = rememberNavBackStack<Route>(Route.Splash)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Route.Splash> {
                SplashScreen(
                    navigate = { route ->
                        backStack.add(route)
                        backStack.remove(Route.Splash)
                    }
                )
            }

            entry<Route.Onboarding> {
                OnboardingScreen(
                    navigateToSignUp = {
                        backStack.add(Route.SignUp)
                        backStack.remove(Route.Onboarding)
                    }
                )
            }

            entry<Route.SignUp> {
                SignUpScreen(
                    navigateVerification = { email ->
                        backStack.add(Route.Verification(email))
                    }
                )
            }

            entry<Route.Verification> { key ->
                VerificationScreen(
                    vm = koinViewModel {
                        parametersOf(key.email)
                    }
                )
            }
        }
    )
}