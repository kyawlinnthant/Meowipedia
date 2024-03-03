package com.everest.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.domain.usecase.Register
import com.everest.navigation.navigator.AppNavigator
import com.everest.util.result.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val register: Register,
    private val appNavigator: AppNavigator
) : ViewModel() {

    private val _vmState = MutableStateFlow(RegisterState())
    val uiState = _vmState.map(RegisterState::asUiState).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = _vmState.value.asUiState()
    )


    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.Register -> doRegister(action.mail, action.password)
        }
    }

    private fun doRegister(mail: String, password: String) {
        viewModelScope.launch {
            _vmState.update {
                it.copy(
                    isLoading = true,
                )
            }
            delay(3000L)

            val result = register.invoke(mail, password)
            when (result) {
                is DataResult.Failed -> println(">>>>> EEEEE ")
                is DataResult.Success -> {
                    _vmState.update {
                        it.copy(
                            isSuccess = true,
                        )
                    }
                    appNavigator.back()
                }
            }
        }
    }


}
