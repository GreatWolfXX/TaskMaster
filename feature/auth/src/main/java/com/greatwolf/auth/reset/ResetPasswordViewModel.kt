package com.greatwolf.auth.reset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greatwolf.ui.util.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

data class ResetPasswordUiState(
    val email: String = "",
    val emailError: UiText? = null,
    val isPhoneWay: Boolean = false,
    val snackbarMessage: UiText? = null,
    val loading: Boolean = false
)

sealed class ResetPasswordIntent {
    data class EnterEmail(val email: String) : ResetPasswordIntent()
    data object Submit : ResetPasswordIntent()
}

sealed class ResetPasswordEvent {
    data object Idle : ResetPasswordEvent()
    data object Submit : ResetPasswordEvent()
}

class ResetPasswordViewModel(
) : ViewModel() {

    private val _state = MutableStateFlow<ResetPasswordUiState>(ResetPasswordUiState())
    val state: StateFlow<ResetPasswordUiState> = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ResetPasswordUiState()
        )

    private var _event: Channel<ResetPasswordEvent> = Channel()
    val event = _event.receiveAsFlow()

    fun onIntent(intent: ResetPasswordIntent) {
        when (intent) {
            is ResetPasswordIntent.EnterEmail -> {
                _state.update { it.copy(email = intent.email) }
            }


            ResetPasswordIntent.Submit -> {

            }
        }
    }
}