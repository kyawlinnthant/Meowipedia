package com.everest.presentation.signin

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.domain.usecase.SignIn
import com.everest.extensions.isValidEmail
import com.everest.navigation.navigator.AppNavigator
import com.everest.util.result.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signIn: SignIn,
    private val appNavigator: AppNavigator
) : ViewModel() {

    @OptIn(ExperimentalFoundationApi::class)
    val mail = TextFieldState()

    @OptIn(ExperimentalFoundationApi::class)
    val password = TextFieldState()

    private val _signInEvent = MutableSharedFlow<SignInEvent>()
    val signInEvent = _signInEvent.asSharedFlow()

    private val _vmState = MutableStateFlow(SignInState())
    val uiState = _vmState.map(SignInState::asUiState).stateIn(
        scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = _vmState.value.asUiState()
    )


    fun onAction(action: SignInAction) {
        when (action) {
            is SignInAction.Navigate -> appNavigator.to(action.route)
            is SignInAction.SignIn -> doSign()
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun doSign() {
        viewModelScope.launch {
            _vmState.update {
                it.copy(
                    isLoading = true,
                )
            }
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
}
