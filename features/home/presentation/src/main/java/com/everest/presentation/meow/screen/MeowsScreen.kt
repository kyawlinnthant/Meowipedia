package com.everest.presentation.meow.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.everest.domain.model.meow.MeowVo
import com.everest.navigation.Screens
import com.everest.presentation.meow.view.list.MeowsCompactList
import com.everest.presentation.meow.view.list.MeowsExpandedList
import com.everest.presentation.meow.view.list.MeowsMediumList
import com.everest.presentation.meow.view.loading.MeowsCompactLoading
import com.everest.presentation.meow.view.loading.MeowsExpandedLoading
import com.everest.presentation.meow.view.loading.MeowsMediumLoading
import com.everest.theme.WindowSize
import com.everest.theme.WindowType
import com.everest.ui.screen.FullScreenErrorView
import com.everest.util.result.NetworkError
import com.everest.util.result.toErrorType

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MeowsScreen(
    windowSize: WindowSize,
    meows: LazyPagingItems<MeowVo>,
    onAction: (MeowsAction) -> Unit
) {
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()
    val lazyListState = rememberLazyListState()

    val isScrolling by remember(meows) {
        derivedStateOf {
            if (meows.itemCount == 0) {
                false
            } else {
                when (windowSize.width) {
                    WindowType.Compact -> lazyListState.isScrollInProgress
                    WindowType.Medium -> lazyStaggeredGridState.isScrollInProgress
                    WindowType.Expanded -> lazyStaggeredGridState.isScrollInProgress
                }
            }
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            AnimatedVisibility(
                visible = !isScrolling,
                enter = fadeIn() + expandHorizontally(),
                exit = fadeOut() + shrinkHorizontally()
            ) {
                BottomAppBar(
                    actions = {
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(
                            onClick = { onAction(MeowsAction.Navigate(route = Screens.Categories.route)) },
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
                        ) {
                            Icon(Icons.Filled.Menu, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(
                            onClick = { onAction(MeowsAction.Navigate(route = Screens.Settings.route)) },
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.2f))
                        ) {
                            Icon(Icons.Filled.Settings, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface)
                        }
                    },
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent
                )
            }
        }
    ) {
        meows.apply {
            if (

                loadState.refresh is LoadState.Loading &&
                loadState.source.refresh is LoadState.Loading &&
                loadState.mediator?.refresh is LoadState.Loading &&
                this.itemCount == 0
            ) {
                when (windowSize.width) {
                    WindowType.Compact -> MeowsCompactLoading()
                    WindowType.Medium -> MeowsMediumLoading()
                    WindowType.Expanded -> MeowsExpandedLoading()
                }
                return@apply
            }

            if (
                loadState.refresh is LoadState.Error &&
                loadState.mediator?.refresh is LoadState.Error &&
                this.itemCount == 0
            ) {
                val throwable = (loadState.refresh as LoadState.Error).error
                val errorType = throwable.toErrorType()

                FullScreenErrorView(type = errorType) {
                    this.retry()
                }
                return@apply
            }

            when (windowSize.width) {
                WindowType.Compact -> MeowsCompactList(
                    meows = this,
                    listState = lazyListState,
                    onRetry = { retry() },
                    onItemClick = {}
                )

                WindowType.Medium -> MeowsMediumList(
                    meows = this.itemSnapshotList.items,
                    isLoading = loadState.mediator?.refresh is LoadState.Loading && this@apply.itemCount != 0,
                    isError = loadState.mediator?.refresh is LoadState.Error && this@apply.itemCount != 0,
                    isEnd = loadState.append.endOfPaginationReached && this@apply.itemCount != 0,
                    error = if (loadState.mediator?.refresh is LoadState.Error && this@apply.itemCount != 0) (loadState.mediator?.refresh as LoadState.Error).error.toErrorType() else NetworkError.SomethingWrong,
                    listState = lazyStaggeredGridState,
                    onRetry = { retry() },
                    onItemClick = {}
                )

                WindowType.Expanded -> MeowsExpandedList(
                    meows = this.itemSnapshotList.items,
                    isLoading = loadState.mediator?.refresh is LoadState.Loading && this@apply.itemCount != 0,
                    isError = loadState.mediator?.refresh is LoadState.Error && this@apply.itemCount != 0,
                    isEnd = loadState.append.endOfPaginationReached && this@apply.itemCount != 0,
                    error = if (loadState.mediator?.refresh is LoadState.Error && this@apply.itemCount != 0) (loadState.mediator?.refresh as LoadState.Error).error.toErrorType() else NetworkError.SomethingWrong,
                    listState = lazyStaggeredGridState,
                    onRetry = { retry() },
                    onItemClick = {}
                )
            }
        }
    }
}
