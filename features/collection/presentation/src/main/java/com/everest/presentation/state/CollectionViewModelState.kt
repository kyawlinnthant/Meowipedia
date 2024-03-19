package com.everest.presentation.state

import androidx.paging.PagingData
import androidx.paging.filter
import com.everest.domain.model.CollectionVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

data class CollectionViewModelState(
    val showOwnCollection: Boolean = false,
//    val collectionList: List<CollectionVO> = emptyList(),
    val collectionList: Flow<PagingData<CollectionVO>> = emptyFlow(),
//    val listState: CollectionOwnState = CollectionOwnState(),
//    val ownState: CollectionOwnState = CollectionOwnState()
) {
    fun asOwnCollectionState() = collectionList.map {
        it.filter { item ->
            item.id == "24242"
        }
    }

    fun asListState() = collectionList
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
