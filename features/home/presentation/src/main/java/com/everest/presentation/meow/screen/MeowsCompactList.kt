package com.everest.presentation.meow.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.everest.domain.model.meow.MeowVo
import com.everest.home.presentation.R
import com.everest.presentation.meow.item.GalleryEndItem
import com.everest.presentation.meow.item.GalleryErrorItem
import com.everest.presentation.meow.item.GalleryLoadingItem
import com.everest.presentation.meow.item.MeowItem
import com.everest.ui.text.asErrorMessage
import com.everest.util.result.NetworkError

@Composable
fun MeowsCompactList(
    modifier: Modifier = Modifier,
    meows: List<MeowVo>,
    isLoading: Boolean,
    isError: Boolean,
    isEnd: Boolean,
    error: NetworkError = NetworkError.SomethingWrong,
    listState: LazyListState,
    onRetry : () -> Unit
) {

    val errorMessage = asErrorMessage(error = error)
    LazyColumn(
        modifier = modifier.fillMaxSize(),
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

    /* meows.apply {

         LazyColumn(
             modifier = modifier.fillMaxSize(),
             state = listState
         ) {
             items(
                 count = meows.itemCount,
                 key = meows.itemKey { it.id }
             ) { index ->

                 MeowItem(
                     index = index,
                     meowVo = meows[index] ?: MeowVo()
                 )
             }
             item {
                 if (loadState.mediator?.refresh is LoadState.Loading && this@apply.itemCount != 0) {
                     GalleryLoadingItem(
                         modifier = Modifier
                             .navigationBarsPadding()
                             .padding(bottom = 80.dp)
                     )
                 }
             }

             item {
                 if (loadState.mediator?.refresh is LoadState.Error && this@apply.itemCount != 0) {
                     val error = (loadState.mediator!!.refresh as LoadState.Error).error.message
                     GalleryErrorItem(
                         message = error ?: "Something's wrong",
                         modifier = Modifier
                             .navigationBarsPadding()
                             .padding(bottom = 80.dp)
                     ) {
                         retry()
                     }
                 }
             }
             item {
                 if (loadState.append.endOfPaginationReached && this@apply.itemCount != 0) {
                     GalleryEndItem(
                         modifier = Modifier
                             .navigationBarsPadding()
                             .padding(bottom = 80.dp)
                     )
                 }
             }
         }
     }*/

}
