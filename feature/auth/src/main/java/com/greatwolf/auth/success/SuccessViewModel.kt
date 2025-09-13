package com.greatwolf.auth.success

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greatwolf.domain.repository.AuthRepository
import com.greatwolf.ui.util.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn

data class SuccessUiState(
    val snackbarMessage: UiText? = null,
    val loading: Boolean = false
)

sealed class SuccessIntent {
    data object Submit : SuccessIntent()
}

sealed class SuccessEvent {
    data object Idle : SuccessEvent()
    data object Submit : SuccessEvent()
}

class SuccessViewModel: ViewModel() {

    private val _state = MutableStateFlow<SuccessUiState>(SuccessUiState())
    val state: StateFlow<SuccessUiState> = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SuccessUiState()
        )

    private var _event: Channel<SuccessEvent> = Channel()
    val event = _event.receiveAsFlow()

    fun onIntent(intent: SuccessIntent) {
        when (intent) {
            SuccessIntent.Submit -> {

            }
        }
    }
}