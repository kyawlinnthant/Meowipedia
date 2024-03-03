package com.everest.presentation.meow.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everest.domain.model.meow.MeowVo
import com.everest.presentation.meow.item.GalleryEndItem
import com.everest.presentation.meow.item.GalleryErrorItem
import com.everest.presentation.meow.item.GalleryLoadingItem
import com.everest.presentation.meow.item.MeowItem
import com.everest.ui.text.asErrorMessage
import com.everest.util.result.NetworkError

@Composable
fun MeowsExpandedList(
    modifier: Modifier = Modifier,
    meows: List<MeowVo>,
    isLoading: Boolean,
    isError: Boolean,
    isEnd: Boolean,
    error: NetworkError = NetworkError.SomethingWrong,
    listState: LazyStaggeredGridState,
    onRetry: () -> Unit
) {

    val errorMessage = asErrorMessage(error = error)

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        modifier = modifier.fillMaxSize(),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        state = listState
    ) {

        items(
            count = meows.size,
            key = {
                meows[it].id
            }
        ) {
            MeowItem(
                index = it,
                meowVo = meows[it]
            )
        }

        item {
            if (isLoading) {

                GalleryLoadingItem(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(bottom = 80.dp)
                )
            }
        }
        item {
            if (isError) {
                GalleryErrorItem(
                    message = errorMessage,
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(bottom = 80.dp)
                ) {
                    onRetry()
                }
            }
        }
        item {
            if (isEnd) {
                GalleryEndItem(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(bottom = 80.dp)
                )
            }
        }
    }

}
