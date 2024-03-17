package com.everest.presentation.view

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.os.LocaleListCompat
import com.everest.extensions.getLocaleFromLanguageTags
import com.everest.navigation.Screens
import com.everest.presentation.SettingsAction
import com.everest.presentation.item.CollectionSection
import com.everest.presentation.item.DynamicSectionItem
import com.everest.presentation.item.LanguageSection
import com.everest.presentation.item.ThemeSection
import com.everest.settings.presentation.R
import com.everest.type.DayNightTheme
import com.everest.type.toStringLanguageType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    theme: DayNightTheme,
    dynamicEnabled: Boolean,
    onRestart: () -> Unit,
    onAction: (SettingsAction) -> Unit,
    isSupportDynamic: Boolean
) {
    val context = LocalContext.current
    val languageCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val currentAppLocales: LocaleList = context.getSystemService(LocaleManager::class.java).applicationLocales
        currentAppLocales.toLanguageTags().getLocaleFromLanguageTags()
    } else {
        AppCompatDelegate.getApplicationLocales().toLanguageTags()
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(id = R.string.settings)) }, navigationIcon = {
            IconButton(onClick = { onAction(SettingsAction.OnBackPress) }) {
                Icon(painter = painterResource(id = R.drawable.baseline_keyboard_arrow_left_24), contentDescription = "Back")
            }
        })
    }) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                ThemeSection(selected = theme, onUpdate = { theme ->
                    onAction(SettingsAction.UpdateTheme(theme))
                })

                LanguageSection(selected = languageCode.toStringLanguageType(), onUpdate = { languageType ->
                    changeLanguage(value = languageType.name.lowercase(), context = context, onRestart = {
                        onRestart()
                    })
                })
                CollectionSection {
                    onAction(SettingsAction.Navigate(Screens.Collection.route))
                }
            }
            if (isSupportDynamic) {
                item {
                    DynamicSectionItem(enabled = dynamicEnabled, onUpdate = { enabled ->
                        onAction(SettingsAction.UpdateDynamic(enabled))
                    })
                }
            }
        }
    }
}

private fun changeLanguage(value: String, context: Context, onRestart: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        context.getSystemService(LocaleManager::class.java).applicationLocales = LocaleList.forLanguageTags(value)
    } else {
        val locales = LocaleListCompat.forLanguageTags(value)
        AppCompatDelegate.setApplicationLocales(locales)
        onRestart()
    }
}
