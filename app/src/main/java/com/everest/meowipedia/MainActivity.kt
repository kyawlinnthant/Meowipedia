package com.everest.meowipedia

import android.app.LocaleManager
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.everest.meowipedia.navigation.MeowGraph
import com.everest.meowipedia.view.MainViewModel
import com.everest.navigation.NavigationInstructor
import com.everest.theme.MeowipediaTheme
import com.everest.theme.rememberWindowSize
import com.everest.type.toStringLanguageType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val window = rememberWindowSize()
            val controller = rememberNavController()
//            val vm: MainViewModel = hiltViewModel()
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

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            return
        }
        val currentAppLocales = getSystemService(LocaleManager::class.java).applicationLocales
        val languageCode = currentAppLocales.toLanguageTags().substringBefore('-')
        vm.putLanguage(languageCode.toStringLanguageType())
    }
}
