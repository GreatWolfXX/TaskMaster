package com.greatwolf.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greatwolf.domain.repository.SettingsRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed class OnboardingIntent {
    data object FinishClicked : OnboardingIntent()
}

sealed class OnboardingEvent {
    data object Idle : OnboardingEvent()
    data object ShowErrorSnackbar : OnboardingEvent()
    data object Finish : OnboardingEvent()
}

class OnboardingViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private var _event: Channel<OnboardingEvent> = Channel()
    val event = _event.receiveAsFlow()

    fun onIntent(intent: OnboardingIntent) {
        when (intent) {
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