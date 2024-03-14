package com.everest.presentation.meow.view.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.everest.domain.model.meow.MeowVo
import com.everest.presentation.meow.item.MeowItem
import com.everest.theme.dimen
import com.everest.ui.item.EndItem
import com.everest.ui.item.ErrorItem
import com.everest.ui.item.LoadingItem
import com.everest.ui.text.asErrorMessage
import com.everest.util.result.toErrorType

@Composable
fun MeowsCompactList(
    modifier: Modifier = Modifier,
    meows: LazyPagingItems<MeowVo>,
    listState: LazyListState,
    onRetry: () -> Unit,
    onItemClick: (MeowVo) -> Unit
) {
    meows.apply {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            state = listState
        ) {
            items(
                count = meows.itemCount,
                key = (meows.itemKey { it.id })
            ) { index ->
                val current = meows[index]
                current?.let {
                    MeowItem(meowVo = it) {
                        onItemClick(it)
                    }
                }
            }

            item {
                if (loadState.mediator?.append is LoadState.Loading) {
                    LoadingItem()
                    Spacer(modifier = modifier.padding(bottom = MaterialTheme.dimen.bottomAppBarHeight))
                }
            }
            item {
                if (loadState.mediator?.append is LoadState.Error) {
                    val throwable = (loadState.mediator!!.append as LoadState.Error).error
                    val errorType = throwable.toErrorType()
                    val errorMessage = asErrorMessage(error = errorType)
                    ErrorItem(message = errorMessage) {
                        onRetry()
                    }
                    Spacer(modifier = modifier.padding(bottom = MaterialTheme.dimen.bottomAppBarHeight))
                }
            }

            item {
                loadState.mediator?.let { state ->
                    if (state.append.endOfPaginationReached) {
                        EndItem()
                        Spacer(modifier = modifier.padding(bottom = MaterialTheme.dimen.bottomAppBarHeight))
                    }
                }
            }

            item {
                Spacer(modifier = modifier.navigationBarsPadding())
            }
        }
    }
}
