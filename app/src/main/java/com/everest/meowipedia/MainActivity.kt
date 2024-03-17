package com.everest.meowipedia

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.everest.meowipedia.navigation.MeowGraph
import com.everest.meowipedia.view.MainViewModel
import com.everest.navigation.NavigationInstructor
import com.everest.theme.MeowipediaTheme
import com.everest.theme.rememberWindowSize
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val vm: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val window = rememberWindowSize()
            val controller = rememberNavController()
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

    override fun attachBaseContext(newBase: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            val currentLang = AppCompatDelegate.getApplicationLocales().toLanguageTags()
            newBase.resources.configuration.setLocale(Locale(currentLang))

            applyOverrideConfiguration(newBase.resources.configuration)
            super.attachBaseContext(newBase)
        } else {
            super.attachBaseContext(newBase)
        }
    }
}
