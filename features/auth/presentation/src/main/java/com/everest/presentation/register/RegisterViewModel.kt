package com.everest.presentation.register

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.domain.usecase.Register
import com.everest.navigation.navigator.AppNavigator
import com.everest.util.result.DataResult
import com.everest.util.validator.InputValidator
import com.everest.util.validator.PasswordValidator
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val register: Register,
    private val appNavigator: AppNavigator,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _registerUserInfoState = MutableStateFlow(RegisterUserInfoState())
    val registerUserInfoState = _registerUserInfoState.asStateFlow()

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
            is RegisterAction.Register -> validUserInfo()
        }
    }

    private fun doRegister() {
        viewModelScope.launch {
            _vmState.update {
                it.copy(
                    isLoading = true
                )
            }
            when (
                val result = register.invoke(
                    registerUserInfoState.value.mail,
                    registerUserInfoState.value.password
                )
            ) {
                is DataResult.Failed -> {
                    _vmState.update { state ->
                        state.copy(
                            isLoading = false
                        )
                    }
                    firebaseAuth.signOut()
                    _registerEvent.emit(RegisterEvent.ShowSnack(result.error))
                }

                is DataResult.Success -> {
                    result.data
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

    @OptIn(ExperimentalFoundationApi::class)
    private fun validUserInfo() {
        _registerUserInfoState.update {
            it.copy(
                mail = registerUserInfoState.value.mailTextFieldState.text.toString().trim(),
                password = registerUserInfoState.value.passwordTextFieldState.text.toString().trim(),
                confirmPassword = registerUserInfoState.value.confirmPasswordTextFieldState.text.toString().trim()
            )
        }
        val mailMessage = InputValidator.emailValidator(_registerUserInfoState.value.mail)
        val passwordMessage = PasswordValidator.isPasswordValid(_registerUserInfoState.value.password)
        val confirmPasswordMessage = PasswordValidator.isPasswordValid(_registerUserInfoState.value.confirmPassword)

        _registerUserInfoState.update {
            it.copy(
                mailErrorMessage = mailMessage,
                passwordErrorMessage = passwordMessage,
                confirmPasswordErrorMessage = confirmPasswordMessage
            )
        }

        if (registerUserInfoState.value.password.isNotEmpty() && registerUserInfoState.value.confirmPassword.isNotEmpty()) {
            val passwordMatchMessage = PasswordValidator.isPasswordMatch(registerUserInfoState.value.password, registerUserInfoState.value.confirmPassword)
            _registerUserInfoState.update {
                it.copy(
                    passwordErrorMessage = passwordMatchMessage,
                    confirmPasswordErrorMessage = passwordMatchMessage
                )
            }
        }

        isValidUserInfo()
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun isValidUserInfo() {
        if (registerUserInfoState.value.passwordErrorMessage.isEmpty() &&
            registerUserInfoState.value.mailErrorMessage.isEmpty() &&
            registerUserInfoState.value.confirmPasswordErrorMessage.isEmpty()
        ) {
            _registerUserInfoState.update {
                it.copy(
                    isValid = true
                )
            }
        } else {
            _registerUserInfoState.update {
                it.copy(
                    isValid = false
                )
            }
        }

        if (registerUserInfoState.value.isValid) {
            doRegister()
        }
    }
}
