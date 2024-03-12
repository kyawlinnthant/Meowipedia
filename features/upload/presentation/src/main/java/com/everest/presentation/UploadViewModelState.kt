package com.everest.presentation

import com.everest.util.result.NetworkError

data class UploadViewModelState(
    val state: UploadState = UploadState()
) {
    fun asUiState() = state.asUIState()
}

sealed interface UploadViewModelUiState {
    data class ScreenState(val state: UploadUiState) : UploadViewModelUiState
}

data class UploadState(
    val isNormal: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: NetworkError? = null,
    val isLoading: Boolean = false
) {
    fun asUIState() = when {
        isSuccess -> UploadUiState.Success
        isLoading -> UploadUiState.Loading
        isError != null -> UploadUiState.Error(isError)
        else -> UploadUiState.Normal
    }
}

sealed interface UploadUiState {

    data object Normal : UploadUiState
    data object Loading : UploadUiState
    data class Error(val error: NetworkError) : UploadUiState
    data object Success : UploadUiState
}
