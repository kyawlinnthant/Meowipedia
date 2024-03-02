package com.everest.presentation.meow.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.everest.domain.model.meow.MeowVo
import com.everest.navigation.Screens
import com.everest.presentation.meow.item.GalleryEndItem
import com.everest.presentation.meow.item.GalleryErrorItem
import com.everest.presentation.meow.item.MeowItem
import com.everest.presentation.meow.item.GalleryLoadingItem
import com.everest.presentation.meow.view.GalleryFirstTimeError
import com.everest.presentation.meow.view.GalleryFirstTimeShimmer
import com.everest.theme.WindowSize
import com.everest.ui.screen.FullScreenErrorView
import com.everest.util.result.toErrorType

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MeowsScreen(
    windowSize: WindowSize,
    galleries: LazyPagingItems<MeowVo>,
    onAction: (MeowsAction) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val isScrolling by remember(galleries) {
        derivedStateOf {
            if(galleries.itemCount == 0) false else lazyListState.isScrollInProgress
        }
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = !isScrolling,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
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
                                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
                        ) {
                            Icon(Icons.Filled.Settings, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface)
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { onAction(MeowsAction.Upload) },
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
                            contentColor = MaterialTheme.colorScheme.onSurface,
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        ) {
                            Icon(Icons.Filled.Add, null)
                        }
                    },
                    containerColor = BottomAppBarDefaults.containerColor.copy(alpha = 0f),


                    )
            }
        }
    ) {
        galleries.apply {
            when {
                loadState.refresh is LoadState.Loading && this.itemCount == 0 -> {
                    GalleryFirstTimeShimmer(paddingValue = it)
                }

                loadState.refresh is LoadState.Error && this.itemCount == 0 -> {
                    val throwable = (loadState.refresh as LoadState.Error).error
                    val errorType = throwable.toErrorType()

                    FullScreenErrorView(type = errorType) {
                        retry()
                    }

                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = lazyListState
            ) {
                items(
                    count = galleries.itemCount,
                    key = galleries.itemKey { it.id }
                ) { index ->

                    MeowItem(
                        index = index,
                        meowVo = galleries[index] ?: MeowVo()
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
        }
    }
}
