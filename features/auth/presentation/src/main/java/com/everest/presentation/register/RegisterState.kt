package com.everest.presentation.register

data class RegisterState(
    val isLoading: Boolean = false
) {
    fun asUiState() = if (isLoading) {
        RegisterUIState.Loading
    } else {
        RegisterUIState.DefaultView
    }
}

sealed interface RegisterUIState {
    data object DefaultView : RegisterUIState
    data object Loading : RegisterUIState
}
