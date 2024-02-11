package com.everest.categories.presentation.categories

import com.everest.categories.domain.vo.CategoryVO
import com.everest.util.result.NetworkError

data class CategoriesViewModelState(
    val searchQuery: String = "",
    val shouldShowSearch : Boolean = false,
    val categories: List<CategoryVO> = emptyList(),
    val isError: NetworkError? = null,
    val isLoading: Boolean = false

) {
    fun asUiState() = when {
        isLoading -> CategoriesUiState.Loading
        isError != null -> CategoriesUiState.Error(isError)
        else -> CategoriesUiState.HasData(categories)
    }
}

sealed interface CategoriesUiState {
    data object Loading : CategoriesUiState
    data class Error(val error: NetworkError) : CategoriesUiState
    data class HasData(val categories: List<CategoryVO>) : CategoriesUiState
}