package com.everest.categories.presentation.categories.state

import com.everest.categories.domain.vo.CategoryVO
import com.everest.util.result.NetworkError

data class CategoriesListState(
    val categories: List<CategoryVO> = emptyList(),
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
    data class HasData(val categories: List<CategoryVO>) : CategoriesListUiState
}
