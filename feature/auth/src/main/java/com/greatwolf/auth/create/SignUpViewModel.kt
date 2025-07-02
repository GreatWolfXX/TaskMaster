package com.greatwolf.auth.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

data class SignUpUiState(
    val email: String = "",
    val password: String = "",
    val passwordRepeat: String = "",
    val isAgreeTerms: Boolean = false,
    val loading: Boolean = false
)

sealed class SignUpIntent {
    data class EnterEmail(val email: String) : SignUpIntent()
    data class EnterPassword(val password: String) : SignUpIntent()
    data class EnterRepeatPassword(val password: String) : SignUpIntent()
    data class ChangeIsAgreeTerms(val isAgreeTerms: Boolean) : SignUpIntent()
}


class SignUpViewModel() : ViewModel() {

    private val _state = MutableStateFlow<SignUpUiState>(SignUpUiState())
    val state: StateFlow<SignUpUiState> = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SignUpUiState()
        )

    fun onIntent(intent: SignUpIntent) {
        when (intent) {
            is SignUpIntent.EnterEmail -> {
                _state.update { it.copy(email = intent.email) }
            }

            is SignUpIntent.EnterPassword -> {
                _state.update { it.copy(password = intent.password) }
            }

            is SignUpIntent.EnterRepeatPassword -> {
                _state.update { it.copy(passwordRepeat = intent.password) }
            }

            is SignUpIntent.ChangeIsAgreeTerms -> {
                _state.update { it.copy(isAgreeTerms = intent.isAgreeTerms) }
            }
        }
    }
}