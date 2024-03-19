package com.everest.presentation.state

data class CollectionViewModelState(
    val showOwnCollection: Boolean = false,
    val listState: CollectionOwnState = CollectionOwnState(),
    val ownState: CollectionOwnState = CollectionOwnState()
) {
    fun asUiState() =
        if (showOwnCollection) {
            CollectionViewModelUiState.OwnCollectionState(
                state = asOwnCollectionState()
            )
        } else {
            CollectionViewModelUiState.ListState(state = asListState())
        }

    private fun asOwnCollectionState() = ownState.asCollectionState()
    private fun asListState() = listState.asCollectionState()
}

sealed interface CollectionViewModelUiState {
    data class OwnCollectionState(val state: CollectionUiState) :
        CollectionViewModelUiState

    data class ListState(val state: CollectionUiState) : CollectionViewModelUiState
}


data class UploadUiState(
    val showLoading: Boolean = false,
    val message: String = "",
)
