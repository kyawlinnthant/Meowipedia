package com.everest.meowipedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.everest.categories.presentation.categories.CategoriesViewModel
import com.everest.categories.presentation.categories.view.CategoriesScreen
import com.everest.theme.MeowipediaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm: CategoriesViewModel = hiltViewModel()
            val state = vm.uiState.collectAsState()
            LaunchedEffect(key1 = true) {
                vm.fetch()
            }
            MeowipediaTheme {
                // A surface container using the 'background' color from the theme
                CategoriesScreen(state = state.value, onAction = vm::onAction)
            }
        }
    }
}
