package com.everest.categories.presentation.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everest.categories.domain.vo.CategoryVO
import com.everest.ui.text.asErrorMessage
import com.everest.util.result.NetworkError

@Composable
fun CategoriesScreen(
    state: CategoriesUiState
) {

    Scaffold(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is CategoriesUiState.Error -> CategoriesErrorView(
                paddingValues = it,
                error = state.error
            )

            is CategoriesUiState.HasData -> CategoriesListView(
                paddingValues = it,
                categories = state.categories
            )

            CategoriesUiState.Loading -> CategoriesLoadingView(paddingValues = it)
        }
    }
}

@Composable
fun CategoriesErrorView(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    error: NetworkError
) {
    val message = asErrorMessage(error = error)

    Box(modifier = modifier.padding(paddingValues), contentAlignment = Alignment.Center) {

        Text(text = message)
    }
}

@Composable
fun CategoriesLoadingView(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
) {
    Box(modifier = modifier.padding(paddingValues), contentAlignment = Alignment.Center) {
        Text("... Loading")
        CircularProgressIndicator()

    }
}

@Composable
fun CategoriesListView(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    categories: List<CategoryVO>
) {
    LazyColumn(modifier = modifier.padding(paddingValues)) {
        items(count = categories.size, key = { index -> categories[index].id }) { index ->
            val currentVo = categories[index]
            Text(text = currentVo.name, modifier = modifier.padding(8.dp))
        }
    }
}