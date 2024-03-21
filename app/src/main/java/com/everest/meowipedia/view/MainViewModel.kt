package com.everest.meowipedia.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.domain.usecase.ListenDynamicStatus
import com.everest.domain.usecase.ListenThemeStatus
import com.everest.navigation.navigator.AppNavigator
import com.everest.type.ThemeType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTheme: ListenThemeStatus,
    private val getDynamic: ListenDynamicStatus,
    val navigator: AppNavigator
) : ViewModel() {

    private val vmState = MutableStateFlow(MainViewModelState())

    val uiTheme = vmState
        .map(MainViewModelState::asTheme)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asTheme()
        )
    val uiDynamic = vmState
        .map(MainViewModelState::asDynamic)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asDynamic()
        )

    init {
        listenTheme()
        listenDynamic()
    }

    private fun listenTheme() {
        viewModelScope.launch {
            getTheme().collect {
                setTheme(it)
            }
        }
    }

    private fun listenDynamic() {
        viewModelScope.launch {
            getDynamic().collect {
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
                dynamic = enabled
            )
        }
    }
}
