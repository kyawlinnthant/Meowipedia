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
import com.everest.navigation.Screens
import com.everest.presentation.SettingsViewModel
import com.everest.presentation.UploadScreen
import com.everest.presentation.UploadViewModel
import com.everest.presentation.breeds.view.CategoriesScreen
import com.everest.presentation.meow.screen.MeowsScreen
import com.everest.presentation.meow.screen.MeowsViewModel
import com.everest.presentation.view.SettingsScreen
import com.everest.theme.WindowSize

@Composable
fun MeowGraph(
    modifier: Modifier = Modifier,
    controller: NavHostController,
    window: WindowSize,
) {
    NavHost(
        navController = controller,
        startDestination = Screens.Meows.route,
        modifier = modifier.fillMaxSize()
    ) {

        composable(route = Screens.Meows.route) {
            val vm: MeowsViewModel = hiltViewModel()
            val galleries = vm.galleries.collectAsLazyPagingItems()
            MeowsScreen(
                windowSize = window,
                galleries = galleries,
                onAction = vm::onAction
            )
        }

        composable(route = Screens.Categories.route) {
            val vm: com.everest.presentation.breeds.CategoriesViewModel = hiltViewModel()
            val state = vm.uiState.collectAsState()
            val categories = vm.categories.collectAsLazyPagingItems()
            CategoriesScreen(
                categories = categories,
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


        composable(route = Screens.Upload.route) {
            val vm: UploadViewModel = hiltViewModel()
            val uiState = vm.uiState.collectAsState()
            val error = vm.errorFlow.collectAsState(null)
            UploadScreen(
                state = uiState.value,
                filePickStatus = error.value,
                onAction = vm::onAction
            )
        }
    }
}
