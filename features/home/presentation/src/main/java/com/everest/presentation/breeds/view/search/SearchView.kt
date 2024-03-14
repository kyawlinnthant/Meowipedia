package com.everest.presentation.breeds.view.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.everest.presentation.breeds.state.CategoriesSearchUiState

@Composable
fun CategoriesSearchView(
    state: CategoriesSearchUiState,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        when (state) {
            is CategoriesSearchUiState.Error -> SearchErrorView(error = state.error)
            is CategoriesSearchUiState.HasData -> SearchHasDataView(categories = state.categories)
            is CategoriesSearchUiState.Histories -> SearchHistoryView(histories = state.histories)
            CategoriesSearchUiState.Loading -> SearchLoadingView()
        }
    }
}
