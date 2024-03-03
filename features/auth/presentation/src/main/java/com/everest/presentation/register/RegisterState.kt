package com.everest.presentation.register

import com.everest.util.result.NetworkError

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val isError: NetworkError? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
) {
    fun asUiState() = if (email.isEmpty() && password.isEmpty()) {
        RegisterUIState.DefaultView
    } else {
        when {
            isLoading -> RegisterUIState.Loading
            isError != null -> RegisterUIState.Error(isError)
            isSuccess -> RegisterUIState.Success
            else -> RegisterUIState.DefaultView
        }
    }
}

sealed interface RegisterUIState {
    data object DefaultView : RegisterUIState
    data object Loading : RegisterUIState
    data class Error(val error: NetworkError) : RegisterUIState
    data object Success : RegisterUIState
}
