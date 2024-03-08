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
import com.everest.presentation.register.RegisterEvent
import com.everest.presentation.register.RegisterScreen
import com.everest.presentation.register.RegisterViewModel
import com.everest.presentation.signin.SignInEvent
import com.everest.presentation.signin.SignInScreen
import com.everest.presentation.signin.SignInViewModel
import com.everest.presentation.view.SettingsScreen
import com.everest.theme.WindowSize
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MeowGraph(
    modifier: Modifier = Modifier,
    controller: NavHostController,
    window: WindowSize,
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    NavHost(
        navController = controller,
        startDestination = Screens.Meows.route,
        modifier = modifier.fillMaxSize()
    ) {

        composable(route = Screens.Register.route) {
            val vm: RegisterViewModel = hiltViewModel()
            val vmState = vm.uiState.collectAsState()
            LaunchedEffect(key1 = true) {
                vm.registerEvent.collectLatest { event ->
                    when (event) {
                        RegisterEvent.DefaultView -> println("DEFAULT")
                        is RegisterEvent.ShowSnack -> {
                            snackbarHostState.showSnackbar(
                                message = "Invalid Credential"
                            )
                        }
                    }
                }
            }

            RegisterScreen(
                state = vmState.value,
                snackbarHostState = snackbarHostState,
                mail = vm.mail,
                password = vm.password,
                onAction = vm::onAction
            )
        }

        composable(route = Screens.Login.route) {
            val vm: SignInViewModel = hiltViewModel()
            val vmState = vm.uiState.collectAsState()
            LaunchedEffect(key1 = true) {
                vm.signInEvent.collectLatest { event ->
                    when (event) {
                        SignInEvent.DefaultView -> println("DEFAULT")
                        is SignInEvent.ShowSnack -> {
                            snackbarHostState.showSnackbar(
                                message = "Invalid Credential"
                            )
                        }
                    }
                }
            }
            SignInScreen(
                windowSize = window,
                state = vmState.value,
                snackbarHostState = snackbarHostState,
                mail = vm.mail,
                password = vm.password,
                onAction = vm::onAction
            )
        }

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
