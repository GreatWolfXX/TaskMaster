package com.greatwolf.auth.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greatwolf.auth.R
import com.greatwolf.common.InputError
import com.greatwolf.common.Result
import com.greatwolf.domain.usecase.ValidatePasswordUseCase
import com.greatwolf.ui.util.UiText
import com.greatwolf.ui.util.asUiText
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

data class NewPasswordUiState(
    val password: String = "",
    val passwordError: UiText? = null,
    val passwordRepeat: String = "",
    val passwordRepeatError: UiText? = null,
    val snackbarMessage: UiText? = null,
    val loading: Boolean = false
)

sealed class NewPasswordIntent {
    data class EnterPassword(val password: String) : NewPasswordIntent()
    data class EnterRepeatPassword(val password: String) : NewPasswordIntent()
    data object Submit : NewPasswordIntent()
}

sealed class NewPasswordEvent {
    data object Idle : NewPasswordEvent()
    data object Submit : NewPasswordEvent()
}

class NewPasswordViewModel(
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<NewPasswordUiState>(NewPasswordUiState())
    val state: StateFlow<NewPasswordUiState> = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = NewPasswordUiState()
        )

    private var _event: Channel<NewPasswordEvent> = Channel()
    val event = _event.receiveAsFlow()

    fun onIntent(intent: NewPasswordIntent) {
        when (intent) {
            is NewPasswordIntent.EnterPassword -> {
                _state.update { it.copy(password = intent.password) }
                validatePassword(_state.value.password)
            }

            is NewPasswordIntent.EnterRepeatPassword -> {
                _state.update { it.copy(passwordRepeat = intent.password) }
                validateRepeatPassword(
                    password = _state.value.password,
                    repeatPassword = _state.value.passwordRepeat
                )
            }

            NewPasswordIntent.Submit -> {
                val hasError = listOf(
                    _state.value.passwordError,
                    _state.value.passwordRepeatError
                ).any { it == null }

                viewModelScope.launch {
                    if (!hasError) {
                        _event.send(NewPasswordEvent.Submit)
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

    private fun validatePassword(password: String) {
        validatePasswordUseCase.invoke(password)
            .map { result ->
                when (result) {
                    is com.greatwolf.common.Result.Error -> {
                        _state.update { it.copy(passwordError = result.error.asUiText()) }
                    }

                    com.greatwolf.common.Result.Loading -> {}
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
}