package com.greatwolf.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greatwolf.common.Result
import com.greatwolf.common.asResult
import com.greatwolf.domain.repository.SettingsRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed class OnboardingIntent {
    data class NextClicked(val page: Int) : OnboardingIntent()
    data object FinishClicked : OnboardingIntent()
}

sealed class OnboardingEvent {
    data object Idle : OnboardingEvent()
    data object ShowErrorSnackbar : OnboardingEvent()
    data class Next(val page: Int) : OnboardingEvent()
    data object Finish : OnboardingEvent()
}

class OnboardingViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private var _event: Channel<OnboardingEvent> = Channel()
    val event = _event.receiveAsFlow()

    fun onIntent(intent: OnboardingIntent) {
        when (intent) {
            is OnboardingIntent.NextClicked -> {
                viewModelScope.launch {
                    _event.send(OnboardingEvent.Next(intent.page))
                }
            }

            OnboardingIntent.FinishClicked -> {
                onboardingSaveState()
            }
        }
    }

    private fun onboardingSaveState() {
        val isCompleted = true
        viewModelScope.launch {
            settingsRepository.setOnboardingState(isCompleted)
            _event.send(OnboardingEvent.Finish)
        }
    }
}