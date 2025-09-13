package com.greatwolf.auth.login

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

data class SignInUiState(
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val isRememberMe: Boolean = false,
    val snackbarMessage: UiText? = null,
    val loading: Boolean = false
)

sealed class SignInIntent {
    data class EnterEmail(val email: String) : SignInIntent()
    data class EnterPassword(val password: String) : SignInIntent()
    data class ChangeIsRememberMe(val isRememberMe: Boolean) : SignInIntent()
    data object Submit : SignInIntent()
}

sealed class SignInEvent {
    data object Idle : SignInEvent()
    data object Submit : SignInEvent()
}

class SignInViewModel(
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow<SignInUiState>(SignInUiState())
    val state: StateFlow<SignInUiState> = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SignInUiState()
        )

    private var _event: Channel<SignInEvent> = Channel()
    val event = _event.receiveAsFlow()

    fun onIntent(intent: SignInIntent) {
        when (intent) {
            is SignInIntent.EnterEmail -> {
                _state.update { it.copy(email = intent.email) }
                validateEmail(_state.value.email)
            }

            is SignInIntent.EnterPassword -> {
                _state.update { it.copy(password = intent.password) }
                validatePassword(_state.value.password)
            }

            is SignInIntent.ChangeIsRememberMe -> {
                _state.update { it.copy(isRememberMe = intent.isRememberMe) }
            }

            SignInIntent.Submit -> {
                val hasError = listOf(
                    _state.value.emailError,
                    _state.value.passwordError,
                ).any { it == null }

                viewModelScope.launch {
                    if (!hasError) {
                        signIn()
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

    private fun signIn() {
        authRepository.signIn(
            email = _state.value.email,
            password = _state.value.password
        ).map { result ->
            when (result) {
                is Result.Error -> {
                    when (result.error) {
                        else -> {
                            _state.update { it.copy(snackbarMessage = result.error.asUiText()) }
                        }
                    }
                    _state.update { it.copy(loading = false) }
                }

                Result.Loading -> {
                    _state.update { it.copy(loading = true) }
                }

                is Result.Success -> {
                    _event.send(SignInEvent.Submit)
                    _state.update { it.copy(loading = false) }
                }
            }
        }.launchIn(viewModelScope)
    }
}