package com.greatwolf.taskmaster

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greatwolf.common.Result
import com.greatwolf.common.asResult
import com.greatwolf.domain.repository.AuthRepository
import com.greatwolf.domain.repository.SettingsRepository
import com.greatwolf.taskmaster.navigation.Route
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SplashUiState(
    val progress: Float = 0f,
    val destination: Route = Route.Onboarding
)

sealed class SplashEvent {
    data object Idle : SplashEvent()
    data object Finish : SplashEvent()
}

const val SPLASH_DELAY = 1500L

class SplashViewModel(
    private val settingsRepository: SettingsRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private var _event: Channel<SplashEvent> = Channel()
    val event = _event.receiveAsFlow()

    private val _state = MutableStateFlow<SplashUiState>(SplashUiState())
    val state: StateFlow<SplashUiState> = _state
        .onStart {
            startSplashLoading()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SplashUiState()
        )

    private suspend fun signOut() {
        authRepository.signOut().first()
    }

    private fun getDestination() {
        combine(
            settingsRepository.isOnboardingCompleted(),
            settingsRepository.isRememberSession()
        ) { onboardingResult, rememberResult ->
            Pair(onboardingResult, rememberResult)
        }.asResult()
            .map { result ->
                when (result) {
                    is Result.Error -> {}
                    Result.Loading -> {}
                    is Result.Success -> {
                        val data = result.data
                        _state.update { it.copy(progress = 1f) }
                        if (data.first) {
                            if (data.second) {
                                _state.update { it.copy(destination = Route.Home) }
                            } else {
                                _state.update { it.copy(destination = Route.SignUp) }
                                signOut()
                            }
                        }
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun startSplashLoading() {
        getDestination()
        viewModelScope.launch {
            delay(SPLASH_DELAY)
            _event.send(SplashEvent.Finish)
        }
    }
}