package com.everest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.datastore.DayNightTheme
import com.everest.navigation.Screens
import com.everest.navigation.navigator.AppNavigator
import com.everest.presentation.state.SettingsViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val useCase: SettingsViewModelUseCase,
    private val appNavigator: AppNavigator
) : ViewModel() {

    private val vmState = MutableStateFlow(SettingsViewModelState())
    val uiTheme = vmState
        .map(SettingsViewModelState::asTheme)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asTheme()
        )
    val uiDynamic = vmState
        .map(SettingsViewModelState::asDynamic)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asDynamic()
        )

    fun onAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.UpdateDynamic -> saveDynamic(action.enabled)
            is SettingsAction.UpdateTheme -> {
                saveTheme(action.theme)
                appNavigator.back()
                appNavigator.to(
                    route = Screens.Upload.route

                )
            }
        }
    }

    fun listenTheme() {
        viewModelScope.launch {
            useCase.listenTheme().collect {
                setTheme(it)
            }
        }
    }

    fun listenDynamic() {
        viewModelScope.launch {
            useCase.listenDynamic().collect {
                setDynamic(it)
            }
        }
    }

    private fun setTheme(theme: DayNightTheme) {
        vmState.update { state ->
            state.copy(
                theme = theme
            )
        }
    }

    private fun setDynamic(enabled: Boolean) {
        vmState.update { state ->
            state.copy(
                isDynamicEnabled = enabled
            )
        }
    }

    private fun saveTheme(theme: DayNightTheme) {
        viewModelScope.launch {
            useCase.saveTheme(theme)
        }
    }

    private fun saveDynamic(enabled: Boolean) {
        viewModelScope.launch {
            useCase.saveDynamic(enabled)
        }
    }
}
