package com.everest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.domain.usecase.SettingsViewModelUseCase
import com.everest.navigation.navigator.AppNavigator
import com.everest.presentation.state.SettingsViewModelState
import com.everest.type.DayNightTheme
import com.everest.type.LanguageType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

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
    val language = vmState
        .map(SettingsViewModelState::asLanguage)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asLanguage()
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
            is SettingsAction.UpdateTheme -> saveTheme(action.theme)
            SettingsAction.OnBackPress -> appNavigator.back()
            is SettingsAction.UpdateLanguage -> saveLanguage(action.languageType)
        }
    }

    fun listenTheme() {
        viewModelScope.launch {
            useCase.listenTheme().collectLatest {
                setTheme(it)
            }
        }
    }

    fun listenLanguage() {
        viewModelScope.launch {
            useCase.listenLanguage().collectLatest {
                setLanguage(it)
            }
        }
    }

    fun listenDynamic() {
        viewModelScope.launch {
            useCase.listenDynamic().collectLatest {
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

    private fun setLanguage(languageType: LanguageType) {
        vmState.update { state ->
            state.copy(
                languageType = languageType
            )
        }
    }

    private fun saveTheme(theme: DayNightTheme) {
        viewModelScope.launch {
            useCase.saveTheme(theme)
        }
    }

    private fun saveLanguage(languageType: LanguageType) {
        viewModelScope.launch {
            useCase.saveLanguage(languageType)
        }
    }

    private fun saveDynamic(enabled: Boolean) {
        viewModelScope.launch {
            useCase.saveDynamic(enabled)
        }
    }

}
