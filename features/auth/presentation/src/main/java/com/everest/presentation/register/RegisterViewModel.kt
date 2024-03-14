package com.everest.presentation.register

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.domain.usecase.Register
import com.everest.navigation.navigator.AppNavigator
import com.everest.util.result.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val register: Register,
    private val appNavigator: AppNavigator
) : ViewModel() {
    @OptIn(ExperimentalFoundationApi::class)
    val mail = TextFieldState()

    @OptIn(ExperimentalFoundationApi::class)
    val password = TextFieldState()
    private val _registerEvent = MutableSharedFlow<RegisterEvent>()
    val registerEvent = _registerEvent.asSharedFlow()

    private val _vmState = MutableStateFlow(RegisterState())
    val uiState = _vmState.map(RegisterState::asUiState).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = _vmState.value.asUiState()
    )

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.Register -> doRegister()
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun doRegister() {
        viewModelScope.launch {
            _vmState.update {
                it.copy(
                    isLoading = true
                )
            }
            when (val result = register.invoke(mail.text.toString(), password.text.toString())) {
                is DataResult.Failed -> {
                    _vmState.update { state ->
                        state.copy(
                            isLoading = false
                        )
                    }
                    _registerEvent.emit(RegisterEvent.ShowSnack(result.error))
                }

                is DataResult.Success -> {
                    _vmState.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                    appNavigator.back()
                }
            }
        }
    }
}
