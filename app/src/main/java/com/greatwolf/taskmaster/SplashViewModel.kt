package com.greatwolf.taskmaster

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greatwolf.common.Result
import com.greatwolf.common.asResult
import com.greatwolf.domain.repository.SettingsRepository
import com.greatwolf.taskmaster.navigation.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SplashViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val _destination = MutableStateFlow<Route>(Route.Onboarding)
    val destination: StateFlow<Route> = _destination.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Route.Onboarding
    )

    fun getDestination() {
        settingsRepository.isOnboardingCompleted()
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Error -> {}
                    Result.Loading -> {}
                    is Result.Success -> {
                        if (result.data) {
//                            _destination.update {  }
                        }
                    }
                }
            }
    }

}