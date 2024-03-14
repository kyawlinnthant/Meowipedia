package com.everest.meowipedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.everest.meowipedia.navigation.MeowGraph
import com.everest.meowipedia.view.MainViewModel
import com.everest.navigation.NavigationInstructor
import com.everest.theme.MeowipediaTheme
import com.everest.theme.rememberWindowSize
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val window = rememberWindowSize()
            val controller = rememberNavController()
            val vm: MainViewModel = hiltViewModel()
            val theme = vm.uiTheme.collectAsState()
            val dynamic = vm.uiDynamic.collectAsState()

            NavigationInstructor(
                instructor = vm.navigator.instructor,
                controller = controller
            )
            MeowipediaTheme(
                appTheme = theme.value,
                dynamicColor = dynamic.value
            ) {
                MeowGraph(
                    controller = controller,
                    window = window
                )
            }
        }
    }
}
