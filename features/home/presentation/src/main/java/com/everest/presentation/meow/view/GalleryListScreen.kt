package com.everest.presentation.meow.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.everest.domain.model.meow.MeowVo
import com.everest.presentation.meow.item.GalleryEndItem
import com.everest.presentation.meow.item.GalleryErrorItem
import com.everest.presentation.meow.item.MeowItem
import com.everest.presentation.meow.item.GalleryLoadingItem

@Composable
fun GalleryListScreen(
    galleries: LazyPagingItems<MeowVo>,
    modifier: Modifier = Modifier,
    paddingValue: PaddingValues,
    lazyGridState: LazyStaggeredGridState
) {
    galleries.apply {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValue),
            state = rememberLazyListState()
        ) {
            items(count = galleries.itemCount) { index ->
                val currentItem = galleries[index]
                currentItem?.let {
                    MeowItem(index = index, meowVo = it)
                }
            }
            item {
                if (loadState.append is LoadState.Loading || loadState.mediator?.refresh is LoadState.Loading) {
                    GalleryLoadingItem()
                }
            }
            item {
                if (loadState.append is LoadState.Error) {
                    val error = (loadState.append as LoadState.Error).error.message
                    GalleryErrorItem(message = error ?: "Something's wrong") {
                        retry()
                    }
                }
            }
            item {
                if (loadState.mediator?.refresh is LoadState.Error) {
                    val error = (loadState.mediator!!.refresh as LoadState.Error).error.message
                    GalleryErrorItem(message = error ?: "Something's wrong") {
                        retry()
                    }
                }
            }
            item {
                if (loadState.append.endOfPaginationReached) {
                    GalleryEndItem()
                }
            }
        }

       /* LazyVerticalStaggeredGrid(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValue),
            state = lazyGridState,
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            content = {
                items(count = galleries.itemCount) { index ->
                    val currentItem = galleries[index]
                    currentItem?.let {
                        GalleryItem(index = index, meowVo = it)
                    }
                }
                item {
                    if (loadState.append is LoadState.Loading || loadState.mediator?.refresh is LoadState.Loading) {
                        GalleryLoadingItem()
                    }
                }
                item {
                    if (loadState.append is LoadState.Error) {
                        val error = (loadState.append as LoadState.Error).error.message
                        GalleryErrorItem(message = error ?: "Something's wrong") {
                            retry()
                        }
                    }
                }
                item {
                    if (loadState.mediator?.refresh is LoadState.Error) {
                        val error = (loadState.mediator!!.refresh as LoadState.Error).error.message
                        GalleryErrorItem(message = error ?: "Something's wrong") {
                            retry()
                        }
                    }
                }
                item {
                    if (loadState.append.endOfPaginationReached) {
                        GalleryEndItem()
                    }
                }
            }
        )*/
    }
}
