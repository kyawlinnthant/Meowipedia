package com.everest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.domain.usecase.SettingsViewModelUseCase
import com.everest.navigation.navigator.AppNavigator
import com.everest.presentation.state.SettingsViewModelState
import com.everest.type.ThemeType
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val useCase: SettingsViewModelUseCase,
    private val appNavigator: AppNavigator,
    private val firebaseAuth: FirebaseAuth
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

    val uiLogin = vmState
        .map(SettingsViewModelState::asLogin)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asLogin()
        )

    init {

        firebaseAuth.addAuthStateListener {
            setLogin(it.currentUser != null)
        }
    }

    fun onAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.UpdateDynamic -> saveDynamic(action.enabled)
            is SettingsAction.UpdateTheme -> saveTheme(action.theme)
            SettingsAction.OnBackPress -> appNavigator.back()
            is SettingsAction.Navigate -> appNavigator.to(action.route)
            SettingsAction.Logout -> logoutUser()
        }
    }

    private fun logoutUser() {
        firebaseAuth.signOut()
    }

    fun listenTheme() {
        viewModelScope.launch {
            useCase.listenTheme().collectLatest {
                setTheme(it)
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

    private fun setTheme(theme: ThemeType) {
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

    private fun setLogin(isLogin: Boolean) {
        vmState.update { state ->
            state.copy(
                isLogin = isLogin
            )
        }
    }

    private fun saveTheme(theme: ThemeType) {
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
