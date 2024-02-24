package com.everest.presentation.meow.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.everest.domain.model.meow.MeowVo
import com.everest.navigation.Screens
import com.everest.presentation.meow.item.GalleryEndItem
import com.everest.presentation.meow.item.GalleryErrorItem
import com.everest.presentation.meow.item.GalleryItem
import com.everest.presentation.meow.item.GalleryLoadingItem
import com.everest.presentation.meow.view.GalleryFirstTimeError
import com.everest.presentation.meow.view.GalleryFirstTimeShimmer

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GalleryScreen(
    galleries: LazyPagingItems<MeowVo>,
    onAction: (GalleryAction) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val isScrolling by remember {
        derivedStateOf {
            lazyListState.isScrollInProgress
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
                        IconButton(onClick = { onAction(GalleryAction.Navigate(route = Screens.Categories.route)) }) {
                            Icon(Icons.Filled.Menu, contentDescription = null)
                        }
                        IconButton(onClick = { onAction(GalleryAction.Navigate(route = Screens.Settings.route)) }) {
                            Icon(Icons.Filled.Settings, contentDescription = null)
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { onAction(GalleryAction.Upload) },
                            containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        ) {
                            Icon(Icons.Filled.Add, null)
                        }
                    },
                    scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior(state = rememberBottomAppBarState())
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
                    val error = (loadState.refresh as LoadState.Error).error.message
                    GalleryFirstTimeError(
                        message = error ?: "Something's wrong",
                        paddingValue = it
                    ) {
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

                    GalleryItem(
                        index = index,
                        meowVo = galleries[index] ?: MeowVo()
                    )
                }
                item {
                    if (loadState.append is LoadState.Loading || loadState.mediator?.refresh is LoadState.Loading) {
                        GalleryLoadingItem(
                            modifier = Modifier
                                .navigationBarsPadding()
                                .padding(bottom = 80.dp)
                        )
                    }
                }
                item {
                    if (loadState.append is LoadState.Error) {
                        val error = (loadState.append as LoadState.Error).error.message
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
                    if (loadState.mediator?.refresh is LoadState.Error) {
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
                    if (loadState.append.endOfPaginationReached) {
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
