package com.everest.presentation.signin

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.domain.usecase.SignIn
import com.everest.navigation.navigator.AppNavigator
import com.everest.util.result.DataResult
import com.everest.util.validator.InputValidator
import com.everest.util.validator.PasswordValidator
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
class SignInViewModel @Inject constructor(
    private val signIn: SignIn,
    private val appNavigator: AppNavigator
) : ViewModel() {

    private val _signUserInfoState = MutableStateFlow(SignInUserInfoState())
    val signUserInfoState = _signUserInfoState.asStateFlow()

    @OptIn(ExperimentalFoundationApi::class)
    val mail = TextFieldState()

    @OptIn(ExperimentalFoundationApi::class)
    val password = TextFieldState()

    private val _signInEvent = MutableSharedFlow<SignInEvent>()
    val signInEvent = _signInEvent.asSharedFlow()

    private val _vmState = MutableStateFlow(SignInState())
    val uiState = _vmState.map(SignInState::asUiState).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = _vmState.value.asUiState()
    )

    fun onAction(action: SignInAction) {
        when (action) {
            is SignInAction.Navigate -> appNavigator.to(action.route)
            is SignInAction.SignIn -> validUserInfo()
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun validUserInfo() {
        _signUserInfoState.update {
            it.copy(
                mail = mail.text.toString().trim(),
                password = password.text.toString().trim()
            )
        }
        val mailMessage = InputValidator.emailValidator(_signUserInfoState.value.mail)
        val passwordMessage = PasswordValidator.isPasswordValid(_signUserInfoState.value.password)

        _signUserInfoState.update {
            it.copy(
                mailErrorMessage = mailMessage,
                passwordErrorMessage = passwordMessage
            )
        }
        isValidUserInfo()
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun doSign() {
        _vmState.update { state ->
            state.copy(
                isLoading = true,
                email = mail.text.toString().trim(),
                password = password.text.toString().trim()
            )
        }
        viewModelScope.launch {
            when (val result = signIn.invoke(mail.text.toString(), password.text.toString())) {
                is DataResult.Failed -> {
                    _vmState.update { state ->
                        state.copy(
                            isLoading = false
                        )
                    }
                    _signInEvent.emit(SignInEvent.ShowSnack(result.error))
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

    private fun isValidUserInfo() {
        if (_signUserInfoState.value.passwordErrorMessage.isEmpty() &&
            _signUserInfoState.value.mailErrorMessage.isEmpty()
        ) {
            _signUserInfoState.value = _signUserInfoState.value.copy(
                isValid = true
            )
        } else {
            _signUserInfoState.value = _signUserInfoState.value.copy(
                isValid = false
            )
        }

        if (signUserInfoState.value.isValid) {
            doSign()
        }
    }
}
