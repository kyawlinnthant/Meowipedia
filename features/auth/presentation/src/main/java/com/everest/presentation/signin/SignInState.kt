package com.everest.presentation.signin

import com.everest.util.result.NetworkError

data class SignInState(
    val email: String = "",
    val password: String = "",
    val isError: NetworkError? = null,
    val isLoading: Boolean = false
) {
    fun asUiState() = if (email.isEmpty() && password.isEmpty()) {
        SignInUIState.DefaultView
    } else {
        when {
            isLoading -> SignInUIState.Loading
            isError != null -> SignInUIState.Error(isError)
            else -> SignInUIState.Success
        }
    }
}

sealed interface SignInUIState {
    data object DefaultView : SignInUIState
    data object Loading : SignInUIState
    data class Error(val error: NetworkError) : SignInUIState
    data object Success : SignInUIState
}
