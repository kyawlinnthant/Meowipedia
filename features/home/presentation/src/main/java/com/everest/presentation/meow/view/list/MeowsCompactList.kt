package com.everest.presentation.meow.view.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everest.domain.model.meow.MeowVo
import com.everest.presentation.meow.item.MeowItem
import com.everest.ui.item.EndItem
import com.everest.ui.item.ErrorItem
import com.everest.ui.item.LoadingItem
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
    onRetry: () -> Unit,
    onItemClick: (MeowVo) -> Unit
) {


    val errorMessage = asErrorMessage(error = error)
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState
    ) {

        items(
            items = meows,
            key = { it.id }
        ) {
            MeowItem(
                meowVo = it
            ) {
                onItemClick(it)
            }
        }

        item {
            if (isLoading) {
                LoadingItem()
            }
        }
        item {
            if (isError) {
                ErrorItem(
                    message = errorMessage
                ) {
                    onRetry()
                }
            }
        }
        item {
            if (isEnd) {
                EndItem()
            }
        }

        item {
            Spacer(
                modifier = modifier
                    .navigationBarsPadding()
                    .padding(bottom = 80.dp)
            )
        }
    }

}
