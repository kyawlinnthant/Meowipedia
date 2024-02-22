package com.everest.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.everest.datastore.DayNightTheme
import com.everest.presentation.SettingsAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    theme: DayNightTheme,
    dynamicEnabled: Boolean,
    onAction: (SettingsAction) -> Unit,
    isSupportDynamic: Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Setting") },
                navigationIcon = {
                    IconButton(onClick = { onAction(SettingsAction.OnBackPress) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                ThemeSection(
                    text = "Light Mode",
                    type = DayNightTheme.Day,
                    selected = theme,
                    onUpdate = { theme -> onAction(SettingsAction.UpdateTheme(theme)) }
                )
            }
            item {
                ThemeSection(
                    text = "Dark Mode",
                    type = DayNightTheme.Night,
                    selected = theme,
                    onUpdate = { theme -> onAction(SettingsAction.UpdateTheme(theme)) }
                )
            }
            item {
                ThemeSection(
                    text = "System Default",
                    type = DayNightTheme.System,
                    selected = theme,
                    onUpdate = { theme -> onAction(SettingsAction.UpdateTheme(theme)) }
                )
            }
            if (isSupportDynamic) {
                item {
                    DynamicSection(
                        enabled = dynamicEnabled,
                        onUpdate = { enabled ->
                            onAction(SettingsAction.UpdateDynamic(enabled))
                        }
                    )
                }
            }
        }
    }
}
