package com.everest.presentation.view

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.everest.presentation.SettingsAction
import com.everest.presentation.item.DynamicSectionItem
import com.everest.presentation.item.LanguageSection
import com.everest.presentation.item.ThemeSectionItem
import com.everest.settings.presentation.R
import com.everest.type.DayNightTheme
import com.everest.type.LanguageType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    theme: DayNightTheme, language: LanguageType, dynamicEnabled: Boolean, onAction: (SettingsAction) -> Unit, isSupportDynamic: Boolean
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(id = R.string.settings)) }, navigationIcon = {
            IconButton(onClick = { onAction(SettingsAction.OnBackPress) }) {
                Icon(painter = painterResource(id = R.drawable.baseline_keyboard_arrow_left_24), contentDescription = "Back")
            }
        })
    }) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                ThemeSectionItem(selected = theme, onUpdate = {
                    onAction(SettingsAction.UpdateTheme(it))
                })

                LanguageSection(selected = language, onUpdate = {
                    onAction(SettingsAction.UpdateLanguage(it))
                })
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
