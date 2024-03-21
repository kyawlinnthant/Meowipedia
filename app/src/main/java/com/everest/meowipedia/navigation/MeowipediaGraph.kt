package com.everest.meowipedia.navigation

import android.os.Build
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.everest.meowipedia.MainActivity
import com.everest.navigation.Screens
import com.everest.presentation.CollectionScreen
import com.everest.presentation.CollectionViewModel
import com.everest.presentation.SettingsViewModel
import com.everest.presentation.breeds.CategoriesViewModel
import com.everest.presentation.breeds.view.CategoriesScreen
import com.everest.presentation.meow.screen.MeowsScreen
import com.everest.presentation.meow.screen.MeowsViewModel
import com.everest.presentation.register.RegisterScreen
import com.everest.presentation.register.RegisterViewModel
import com.everest.presentation.signin.SignInScreen
import com.everest.presentation.signin.SignInViewModel
import com.everest.presentation.view.SettingsScreen
import com.everest.theme.WindowSize

@Composable
fun MeowGraph(
    modifier: Modifier = Modifier,
    controller: NavHostController,
    window: WindowSize
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    NavHost(
        navController = controller,
        startDestination = Screens.HomeGraph.route,
        modifier = modifier.fillMaxSize()
    ) {
        authGraph(
            window = window,
            snackbarHostState = snackbarHostState
        )
        settingGraph()
        homeGraph(window = window)
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.authGraph(
    window: WindowSize,
    snackbarHostState: SnackbarHostState
) {
    navigation(
        startDestination = Screens.Login.route,
        route = Screens.AuthGraph.route
    ) {
        composable(route = Screens.Register.route) {
            val vm: RegisterViewModel = hiltViewModel()
            val vmState = vm.uiState.collectAsState()
            val registerUserInfoState = vm.registerUserInfoState.collectAsState()
            RegisterScreen(
                registerUIState = vmState.value,
                snackbarHostState = snackbarHostState,
                registerUserInfoState = registerUserInfoState.value,
                onAction = vm::onAction
            )
        }

        composable(route = Screens.Login.route) {
            val vm: SignInViewModel = hiltViewModel()
            val vmState = vm.uiState.collectAsState()
            val signUserInfoState = vm.signUserInfoState.collectAsState()
            SignInScreen(
                windowSize = window,
                signUserInfoState = signUserInfoState.value,
                signInUiState = vmState.value,
                snackbarHostState = snackbarHostState,
                mail = vm.mail,
                password = vm.password,
                onAction = vm::onAction
            )
        }
    }
}

fun NavGraphBuilder.settingGraph() {
    navigation(
        startDestination = Screens.Settings.route,
        route = Screens.SettingGraph.route
    ) {
        composable(route = Screens.Settings.route) {
            val context = LocalContext.current
            val vm: SettingsViewModel = hiltViewModel()
            val theme = vm.uiTheme.collectAsState()
            val dynamic = vm.uiDynamic.collectAsState()
            val loginState = vm.uiLogin.collectAsState()
            val isSupportDynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
            LaunchedEffect(key1 = true) {
                vm.listenTheme()
                vm.listenDynamic()
            }
            SettingsScreen(
                theme = theme.value,
                dynamicEnabled = dynamic.value,
                isLogin = loginState.value,
                onAction = vm::onAction,
                onRestart = {
                    (context as MainActivity).recreate()
                },
                isSupportDynamic = isSupportDynamicColor
            )
        }

        composable(route = Screens.Collection.route) {
            val vm: CollectionViewModel = hiltViewModel()
            val isShowOwnCollection = vm.isShowOwnCollection.collectAsState()
            val ownCollectionList = vm.ownCollectionUiState.collectAsState()
            val collectionList = vm.uiState.collectAsState()
            val dialogUiState = vm.uploadUiState.collectAsState()
            LaunchedEffect(key1 = true) {
                vm.getCollection()
            }
            CollectionScreen(
                ownCollectionList = ownCollectionList.value.collectAsLazyPagingItems(),
                collectionList = collectionList.value.collectAsLazyPagingItems(),
                dialogUiState = dialogUiState.value,
                isShowOwnCollection = isShowOwnCollection.value,
                onAction = vm::onAction
            )
        }
    }
}

fun NavGraphBuilder.homeGraph(
    window: WindowSize
) {
    navigation(
        startDestination = Screens.Meows.route,
        route = Screens.HomeGraph.route
    ) {
        composable(route = Screens.Meows.route) {
            val vm: MeowsViewModel = hiltViewModel()
            val galleries = vm.meows.collectAsLazyPagingItems()
            MeowsScreen(
                windowSize = window,
                meows = galleries,
                onAction = vm::onAction
            )
        }

        composable(route = Screens.Categories.route) {
            val vm: CategoriesViewModel = hiltViewModel()
            val state = vm.uiState.collectAsState()
            val categories = vm.categories.collectAsLazyPagingItems()
            CategoriesScreen(
                categories = categories,
                state = state.value,
                onAction = vm::onAction
            )
        }
    }
}
