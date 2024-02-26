package com.everest.presentation.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everest.datastore.DayNightTheme

@Composable
fun ThemeSection(
    modifier: Modifier = Modifier,
    text: String,
    type: DayNightTheme,
    selected: DayNightTheme,
    onUpdate: (DayNightTheme) -> Unit
) {
    val isSelected = type == selected

    Row(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        Text(
            text = text,
            modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        RadioButton(selected = isSelected, onClick = { onUpdate(type) })
    }
}
