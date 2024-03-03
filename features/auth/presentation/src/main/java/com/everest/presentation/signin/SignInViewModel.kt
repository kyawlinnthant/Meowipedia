package com.everest.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.domain.usecase.SignIn
import com.everest.navigation.navigator.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signIn: SignIn,
    private val appNavigator: AppNavigator
) : ViewModel() {

    private val _vmState = MutableStateFlow(SignInState())
    val uiState = _vmState.map(SignInState::asUiState).stateIn(
        scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = _vmState.value.asUiState()
    )


    fun onAction(action: SignInAction) {
        when (action) {
            is SignInAction.Navigate -> appNavigator.to(action.route)
            SignInAction.SignIn -> doSign()
        }
    }

    private fun doSign() {
        viewModelScope.launch {
            _vmState.update {
                it.copy(
                    isLoading = true,
                )
            }
            signIn.invoke("kyawsoewin.dev@gmail.com", "123456")
        }
    }
}
