package com.greatwolf.auth.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greatwolf.auth.R
import com.greatwolf.common.InputError
import com.greatwolf.common.Result
import com.greatwolf.domain.repository.AuthRepository
import com.greatwolf.domain.usecase.ValidatePasswordUseCase
import com.greatwolf.ui.util.UiText
import com.greatwolf.ui.util.asUiText
import com.greatwolf.ui.util.isEmailValid
import com.greatwolf.ui.util.isRepeatPasswordValid
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SignUpUiState(
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val passwordRepeat: String = "",
    val passwordRepeatError: UiText? = null,
    val isAgreeTerms: Boolean = false,
    val snackbarMessage: UiText? = null,
    val loading: Boolean = false
)

sealed class SignUpIntent {
    data class EnterEmail(val email: String) : SignUpIntent()
    data class EnterPassword(val password: String) : SignUpIntent()
    data class EnterRepeatPassword(val password: String) : SignUpIntent()
    data class ChangeIsAgreeTerms(val isAgreeTerms: Boolean) : SignUpIntent()
    data object Submit : SignUpIntent()
}

sealed class SignUpEvent {
    data object Idle : SignUpEvent()
    data object Submit : SignUpEvent()
}

class SignUpViewModel(
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow<SignUpUiState>(SignUpUiState())
    val state: StateFlow<SignUpUiState> = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SignUpUiState()
        )

    private var _event: Channel<SignUpEvent> = Channel()
    val event = _event.receiveAsFlow()

    fun onIntent(intent: SignUpIntent) {
        when (intent) {
            is SignUpIntent.EnterEmail -> {
                _state.update { it.copy(email = intent.email) }
                validateEmail(_state.value.email)
            }

            is SignUpIntent.EnterPassword -> {
                _state.update { it.copy(password = intent.password) }
                validatePassword(_state.value.password)
            }

            is SignUpIntent.EnterRepeatPassword -> {
                _state.update { it.copy(passwordRepeat = intent.password) }
                validateRepeatPassword(
                    password = _state.value.password,
                    repeatPassword = _state.value.passwordRepeat
                )
            }

            is SignUpIntent.ChangeIsAgreeTerms -> {
                _state.update { it.copy(isAgreeTerms = intent.isAgreeTerms) }
            }

            SignUpIntent.Submit -> {
                val hasError = listOf(
                    _state.value.emailError,
                    _state.value.passwordError,
                    _state.value.passwordRepeatError
                ).any { it != null }

                viewModelScope.launch {
                    if (!hasError && _state.value.isAgreeTerms) {
                        signUp()
                    } else {
                        _state.update {
                            it.copy(
                                snackbarMessage =
                                    UiText.StringResource(R.string.snackbar_fill_fields)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun validateEmail(email: String) {
        val isEmailValid = email.isEmailValid()
        if (!isEmailValid) {
            _state.update { it.copy(emailError = InputError.Email.NO_VALID.asUiText()) }
        } else {
            _state.update { it.copy(emailError = null) }
        }
    }

    private fun validatePassword(password: String) {
        validatePasswordUseCase.invoke(password)
            .map { result ->
                when (result) {
                    is Result.Error -> {
                        _state.update { it.copy(passwordError = result.error.asUiText()) }
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        _state.update { it.copy(passwordError = null) }
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun validateRepeatPassword(password: String, repeatPassword: String) {
        val isRepeatPasswordValid = password.isRepeatPasswordValid(repeatPassword)
        if (!isRepeatPasswordValid) {
            _state.update { it.copy(passwordRepeatError = InputError.Password.REPEAT_PASSWORD_NO_MATCH.asUiText()) }
        } else {
            _state.update { it.copy(passwordRepeatError = null) }
        }
    }

    private fun signUp() {
        authRepository.signUp(
            email = _state.value.email,
            password = _state.value.password
        ).map { result ->
            when (result) {
                is Result.Error -> {
                    _state.update { it.copy(loading = false) }
                }

                Result.Loading -> {
                    _state.update { it.copy(loading = true) }
                }

                is Result.Success -> {
                    _event.send(SignUpEvent.Submit)
                    _state.update { it.copy(loading = false) }
                }
            }
        }.launchIn(viewModelScope)
    }
}