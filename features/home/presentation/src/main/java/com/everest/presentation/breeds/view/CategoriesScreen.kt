package com.everest.presentation.breeds.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.everest.domain.model.categories.breed.BreedVo
import com.everest.presentation.breeds.CategoriesAction
import com.everest.presentation.breeds.state.CategoriesViewModelUiState
import com.everest.presentation.breeds.view.list.CategoriesListView
import com.everest.presentation.breeds.view.search.CategoriesSearchView
import com.everest.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    categories: LazyPagingItems<BreedVo>,
    state: CategoriesViewModelUiState,
    onAction: (CategoriesAction) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            when (state) {
                is CategoriesViewModelUiState.ListState -> TopAppBar(title = {
                    Text(text = stringResource(id = R.string.categories))
                }, actions = {
                        IconButton(
                            onClick = {
                                onAction(
                                    CategoriesAction.UpdateSearchView(shouldShow = true)
                                )
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = null
                            )
                        }
                    })

                is CategoriesViewModelUiState.SearchState -> TopAppBar(
                    title = {
                        BasicTextField(
                            value = state.query,
                            onValueChange = {
                                onAction(CategoriesAction.UpdateSearchKey(query = it))
                            },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                onAction(
                                    CategoriesAction.UpdateSearchView(shouldShow = false)
                                )
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_left),
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        }
    ) {
        when (state) {
            is CategoriesViewModelUiState.ListState ->

                categories.apply {
                    when (loadState.refresh) {
                        is LoadState.Error -> {
                            if (loadState.mediator?.refresh is LoadState.Error) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Text(
                                        "Category Error",
                                        modifier = Modifier
                                            .padding(it)
                                    )
                                }
                            }
                        }

                        LoadState.Loading -> {
                            if (loadState.mediator?.refresh is LoadState.Loading) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Text(
                                        "Category Loading",
                                        modifier = Modifier
                                            .padding(it)
                                    )
                                }
                            }
                        }

                        is LoadState.NotLoading -> CategoriesListView(
                            state = state.state,
                            categories = this,
                            paddingValues = it,
                            onAction = onAction
                        )
                    }
                }

            is CategoriesViewModelUiState.SearchState -> CategoriesSearchView(
                state = state.state,
                paddingValues = it
            )
        }
    }
}
