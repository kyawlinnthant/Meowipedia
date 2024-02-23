package com.everest.presentation.breeds.state

data class CategoriesViewModelState(
    val shouldShowSearch: Boolean = false,
    val listState: CategoriesListState = CategoriesListState(),
    val searchState: CategoriesSearchState = CategoriesSearchState()
) {
    fun asUiState() =
        if (shouldShowSearch) {
            CategoriesViewModelUiState.SearchState(
                state = asSearchState(),
                query = searchState.searchQuery
            )
        } else {
            CategoriesViewModelUiState.ListState(state = asListState())
        }

    private fun asSearchState() = searchState.asSearchState()
    private fun asListState() = listState.asListState()
}

sealed interface CategoriesViewModelUiState {
    data class SearchState(val state: CategoriesSearchUiState, val query: String) :
        CategoriesViewModelUiState
    data class ListState(val state: CategoriesListUiState) : CategoriesViewModelUiState
}
