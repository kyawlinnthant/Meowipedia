package com.everest.presentation.gallery.screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.everest.domain.model.gallery.Gallery
import com.everest.home.presentation.R
import com.everest.presentation.gallery.view.GalleryFirstTimeError
import com.everest.presentation.gallery.view.GalleryFirstTimeShimmer
import com.everest.presentation.gallery.view.GalleryListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    galleries: LazyPagingItems<Gallery>,
    onAction: (GalleryAction) -> Unit
) {
    val lazyGridState = rememberLazyStaggeredGridState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.gallery)) },
                actions = {
                    IconButton(
                        onClick = {
                            onAction(
                                GalleryAction.GoToUpload
                            )
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_apps_24),
                            contentDescription = null
                        )
                    }
                    IconButton(
                        onClick = {
                            onAction(
                                GalleryAction.GoToCategories
                            )
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_apps_24),
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        galleries.apply {
            when (loadState.refresh) {
                is LoadState.Error -> {
                    if (loadState.mediator?.refresh is LoadState.Error) {
                        val error = (loadState.refresh as LoadState.Error).error.message
                        GalleryFirstTimeError(
                            message = error ?: "Something's wrong",
                            paddingValue = it
                        ) {
                            retry()
                        }
                    }
                }

                LoadState.Loading -> {
                    if (loadState.mediator?.refresh is LoadState.Loading) {
                        GalleryFirstTimeShimmer(paddingValue = it)
                    }
                }

                is LoadState.NotLoading -> GalleryListScreen(galleries = galleries, paddingValue = it, lazyGridState = lazyGridState)
            }
        }
    }
}
