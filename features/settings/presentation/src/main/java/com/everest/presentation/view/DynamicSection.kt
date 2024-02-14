package com.everest.presentation.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DynamicSection(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onUpdate: (Boolean) -> Unit
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(text = "App color with wallpaper")
        Switch(checked = enabled, onCheckedChange = onUpdate)
    }
}
