package com.greatwolf.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greatwolf.models.Project
import com.greatwolf.models.Task
import com.greatwolf.ui.util.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn

data class HomeUiState(
    val listProjects: List<Project> = emptyList<Project>(),
    val listTasks: List<Task> = emptyList<Task>(),
    val snackbarMessage: UiText? = null,
    val loading: Boolean = false
)

sealed class HomeIntent {
    data object Submit : HomeIntent()
}

sealed class HomeEvent {
    data object Idle : HomeEvent()
    data object Submit : HomeEvent()
}

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow<HomeUiState>(HomeUiState())
    val state: StateFlow<HomeUiState> = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState()
        )

    private var _event: Channel<HomeEvent> = Channel()
    val event = _event.receiveAsFlow()

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.Submit -> {

            }
        }
    }

}