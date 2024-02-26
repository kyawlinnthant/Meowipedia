package com.everest.presentation.breeds.state

import com.everest.domain.model.categories.breed.BreedVo
import com.everest.util.result.NetworkError

data class CategoriesSearchState(
    val searchQuery: String = "",
    val histories: List<String> = emptyList(),
    val categories: List<BreedVo> = emptyList(),
    val isError: NetworkError? = null,
    val isLoading: Boolean = false
) {
    fun asSearchState() = if (searchQuery.isEmpty()) {
        CategoriesSearchUiState.Histories(histories = histories)
    } else {
        when {
            isLoading -> CategoriesSearchUiState.Loading
            isError != null -> CategoriesSearchUiState.Error(isError)
            else -> CategoriesSearchUiState.HasData(categories)
        }
    }
}

sealed interface CategoriesSearchUiState {

    data class Histories(val histories: List<String>) : CategoriesSearchUiState
    data object Loading : CategoriesSearchUiState
    data class Error(val error: NetworkError) : CategoriesSearchUiState
    data class HasData(val categories: List<BreedVo>) : CategoriesSearchUiState
}
