package com.everest.presentation.breeds.state

import com.everest.domain.model.categories.breed.BreedVo
import com.everest.util.result.NetworkError

data class CategoriesListState(
    val categories: List<BreedVo> = emptyList(),
    val isError: NetworkError? = null,
    val isLoading: Boolean = false
) {
    fun asListState() = when {
        isLoading -> CategoriesListUiState.Loading
        isError != null -> CategoriesListUiState.Error(isError)
        else -> CategoriesListUiState.HasData(categories)
    }
}

sealed interface CategoriesListUiState {
    data object Loading : CategoriesListUiState
    data class Error(val error: NetworkError) : CategoriesListUiState
    data class HasData(val categories: List<BreedVo>) : CategoriesListUiState
}
