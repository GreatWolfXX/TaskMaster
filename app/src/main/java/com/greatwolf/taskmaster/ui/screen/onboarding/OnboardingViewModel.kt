package com.greatwolf.taskmaster.ui.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed class OnboardingIntent {
    data class NextClicked(val page: Int) : OnboardingIntent()
    data object FinishClicked : OnboardingIntent()
}

sealed class OnboardingEvent {
    data object Idle : OnboardingEvent()
    data class Next(val page: Int) : OnboardingEvent()
    data object Finish : OnboardingEvent()
}

class OnboardingViewModel : ViewModel() {

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
                viewModelScope.launch {
                    _event.send(OnboardingEvent.Finish)
                }
            }
        }
    }
}