package com.everest.presentation.categories.view.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.everest.domain.model.categories.CategoryVO
import com.everest.presentation.categories.CategoriesAction
import com.everest.presentation.categories.state.CategoriesListUiState

@Composable
fun CategoriesListView(
    categories: LazyPagingItems<CategoryVO>,
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
        ListHasDataView(categories = categories, onAction = onAction)
//        when (state) {
//            is CategoriesListUiState.Error -> ListErrorView(error = state.error)
//            is CategoriesListUiState.HasData -> ListHasDataView(categories = state.categories, onAction = onAction)
//            CategoriesListUiState.Loading -> ListLoadingView()
//        }
    }
}
