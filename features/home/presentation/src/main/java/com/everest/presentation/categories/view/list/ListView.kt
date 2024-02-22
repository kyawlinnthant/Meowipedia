package com.everest.presentation.categories.view.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.everest.presentation.categories.CategoriesAction
import com.everest.presentation.categories.state.CategoriesListUiState

@Composable
fun CategoriesListView(
    state: CategoriesListUiState,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onAction: (CategoriesAction) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        when (state) {
            is CategoriesListUiState.Error -> ListErrorView(error = state.error)
            is CategoriesListUiState.HasData -> ListHasDataView(categories = state.categories, onAction = onAction)
            CategoriesListUiState.Loading -> ListLoadingView()
        }
    }
}
