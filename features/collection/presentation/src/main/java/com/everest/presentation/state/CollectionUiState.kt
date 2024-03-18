package com.everest.presentation.state

import com.everest.domain.model.CollectionVO
import com.everest.util.result.NetworkError

data class CollectionOwnState(
    val collectionList: List<CollectionVO> = emptyList(),
    val isError: NetworkError? = null,
    val isLoading: Boolean = false
) {
    fun asCollectionState() = when {
        isLoading -> CollectionUiState.Loading
        isError != null -> CollectionUiState.Error(isError)
        else -> CollectionUiState.HasData(collectionList)
    }
}

sealed interface CollectionUiState {
    data object Loading : CollectionUiState
    data class Error(val error: NetworkError) : CollectionUiState
    data class HasData(val collectionList: List<CollectionVO>) : CollectionUiState
}
