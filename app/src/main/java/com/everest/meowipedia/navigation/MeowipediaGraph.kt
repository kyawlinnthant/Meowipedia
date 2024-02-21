package com.everest.meowipedia.navigation

import android.os.Build
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.everest.categories.presentation.categories.CategoriesViewModel
import com.everest.categories.presentation.categories.view.CategoriesScreen
import com.everest.navigation.Screens
import com.everest.presentation.SettingsViewModel
import com.everest.presentation.screen.GalleryScreen
import com.everest.presentation.screen.GalleryViewModel
import com.everest.presentation.view.SettingsScreen

@Composable
fun MeowGraph(
    controller: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = controller,
        startDestination = Screens.Galleries.route,
        modifier = modifier.fillMaxSize()
    ) {
        composable(route = Screens.Categories.route) {
            val vm: CategoriesViewModel = hiltViewModel()
            val state = vm.uiState.collectAsState()
            LaunchedEffect(key1 = true) {
                vm.fetch()
            }
            CategoriesScreen(
                state = state.value,
                onAction = vm::onAction
            )
        }

        composable(route = Screens.Settings.route) {
            val vm: SettingsViewModel = hiltViewModel()
            val theme = vm.uiTheme.collectAsState()
            val dynamic = vm.uiDynamic.collectAsState()
            val isSupportDynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
            LaunchedEffect(key1 = true) {
                vm.listenTheme()
                vm.listenDynamic()
            }
            SettingsScreen(
                theme = theme.value,
                dynamicEnabled = dynamic.value,
                onAction = vm::onAction,
                isSupportDynamic = isSupportDynamicColor
            )
        }
        composable(route = Screens.Galleries.route) {
            val vm: GalleryViewModel = hiltViewModel()
            val galleries = vm.galleries.collectAsLazyPagingItems()
            GalleryScreen(
                galleries = galleries,
                onAction = vm::onAction
            )
        }
    }
}
