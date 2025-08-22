package com.greatwolf.auth.verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greatwolf.common.Result
import com.greatwolf.domain.repository.AuthRepository
import com.greatwolf.ui.util.UiText
import io.github.aakira.napier.Napier
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

data class VerificationUiState(
    val email: String = "",
    val otpValue: String = "",
    val otpError: UiText? = null,
    val snackbarMessage: UiText? = null,
    val loading: Boolean = false
)

sealed class VerificationIntent {
    data class EnterOtp(val otp: String) : VerificationIntent()
    data object Resend : VerificationIntent()
    data object Submit : VerificationIntent()
}

sealed class VerificationEvent {
    data object Idle : VerificationEvent()
    data object Submit : VerificationEvent()
}

class VerificationViewModel(
    private val email: String,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow<VerificationUiState>(VerificationUiState())
    val state: StateFlow<VerificationUiState> = _state
        .onStart {
            _state.update { it.copy(email = email) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = VerificationUiState()
        )

    private var _event: Channel<VerificationEvent> = Channel()
    val event = _event.receiveAsFlow()

    fun onIntent(intent: VerificationIntent) {
        when (intent) {
            is VerificationIntent.EnterOtp -> {
                _state.update { it.copy(otpValue = intent.otp) }
            }

            VerificationIntent.Resend -> {
                verificationResend()
            }

            VerificationIntent.Submit -> {
                verification()
            }
        }
    }

    private fun verification() {
        authRepository.signUpOtpVerification(_state.value.email, _state.value.otpValue)
            .map { result ->
                when (result) {
                    is Result.Error -> {
                        _state.update { it.copy(loading = false) }
                    }
                    Result.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }
                    is Result.Success -> {
                        _event.send(VerificationEvent.Submit)
                        _state.update { it.copy(loading = false) }
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun verificationResend() {
        authRepository.signUpOtpVerificationResend(_state.value.email)
            .map { result ->
                when (result) {
                    is Result.Error -> {
                        _state.update { it.copy(loading = false) }
                    }
                    Result.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }
                    is Result.Success -> {
                        _state.update { it.copy(loading = false) }
                    }
                }
            }.launchIn(viewModelScope)
    }
}