package com.greatwolf.auth.verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greatwolf.common.Result
import com.greatwolf.common.asResult
import com.greatwolf.domain.usecase.SignUpVerificationResendUseCase
import com.greatwolf.domain.usecase.SignUpVerificationUseCase
import com.greatwolf.ui.util.UiText
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
    private val signUpVerificationUseCase: SignUpVerificationUseCase,
    private val signUpVerificationResendUseCase: SignUpVerificationResendUseCase
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
        signUpVerificationUseCase.invoke(_state.value.email, _state.value.otpValue)
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Error -> {}
                    Result.Loading -> {}
                    is Result.Success -> {
                        _event.send(VerificationEvent.Submit)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun verificationResend() {
        signUpVerificationResendUseCase.invoke(_state.value.email)
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Error -> {}
                    Result.Loading -> {}
                    is Result.Success -> {}
                }
            }.launchIn(viewModelScope)
    }
}