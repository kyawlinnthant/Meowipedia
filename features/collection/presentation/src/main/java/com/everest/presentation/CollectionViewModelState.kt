package com.everest.presentation

import com.everest.domain.CollectionVO
import com.everest.util.result.NetworkError

data class CollectionViewModelState(
    val state: CollectionState = CollectionState()
) {
    fun asUiState() = state.asUIState()
}


data class CollectionState(
    val collectionList: List<CollectionVO> = listOf(),
    val isError: NetworkError? = null,
    val isLoading: Boolean = false
) {
    fun asUIState() = when {
        isLoading -> CollectionUiState.Loading
        isError != null -> CollectionUiState.Error(isError)
        else -> CollectionUiState.Success(collectionList)
    }
}

sealed interface CollectionUiState {

    data object Loading : CollectionUiState
    data class Error(val error: NetworkError) : CollectionUiState
    data class Success(val data: List<CollectionVO>) : CollectionUiState
}
