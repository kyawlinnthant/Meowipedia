package com.everest.meowipedia

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.core.os.LocaleListCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.everest.meowipedia.navigation.MeowGraph
import com.everest.meowipedia.view.MainViewModel
import com.everest.navigation.NavigationInstructor
import com.everest.theme.MeowipediaTheme
import com.everest.theme.rememberWindowSize
import com.everest.type.LanguageType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val window = rememberWindowSize()
            val controller = rememberNavController()
            val vm: MainViewModel = hiltViewModel()
            val theme = vm.uiTheme.collectAsState()
            val dynamic = vm.uiDynamic.collectAsState()
            LaunchedEffect(key1 = true) {
                vm.language.collectLatest { event ->
                    when (event) {
                        LanguageType.en -> changeLanguage(LanguageType.en.name)

                        LanguageType.zh -> changeLanguage(LanguageType.zh.name)
                    }
                }
            }
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

    private fun changeLanguage(value: String) {
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(
                value
            )
        )
    }
}
