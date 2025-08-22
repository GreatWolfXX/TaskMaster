package com.greatwolf.auth.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greatwolf.common.InputError
import com.greatwolf.ui.util.UiText
import com.greatwolf.ui.util.asUiText
import com.greatwolf.ui.util.isEmailValid
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

data class ProfileUiState(
    val fullName: String = "",
    val fullNameError: UiText? = null,
    val userName: String = "",
    val userNameError: UiText? = null,
    val email: String = "",
    val emailError: UiText? = null,
    val dateBirth: UiText? = null,
    val imageUrl: String = "",
    val snackbarMessage: UiText? = null,
    val loading: Boolean = false
)

sealed class ProfileIntent {
    data class EnterFullName(val fullName: String) : ProfileIntent()
    data class EnterUserName(val userName: String) : ProfileIntent()
    data class EnterEmail(val email: String) : ProfileIntent()
    data object Submit : ProfileIntent()
}

sealed class ProfileEvent {
    data object Idle : ProfileEvent()
    data object Submit : ProfileEvent()
}

class ProfileViewModel(
) : ViewModel() {

    private val _state = MutableStateFlow<ProfileUiState>(ProfileUiState())
    val state: StateFlow<ProfileUiState> = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ProfileUiState()
        )

    private var _event: Channel<ProfileEvent> = Channel()
    val event = _event.receiveAsFlow()

    fun onIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.EnterFullName -> {
                _state.update { it.copy(email = intent.fullName) }
            }

            is ProfileIntent.EnterUserName -> {
                _state.update { it.copy(email = intent.userName) }
            }

            is ProfileIntent.EnterEmail -> {
                _state.update { it.copy(email = intent.email) }
                validateEmail(_state.value.email)
            }

            ProfileIntent.Submit -> {

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
}