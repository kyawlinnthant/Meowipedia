package com.everest.presentation.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.everest.type.DayNightTheme
import com.everest.type.LanguageType

@Composable
fun LanguageSectionItem(
    modifier: Modifier = Modifier,
    text: String,
    type: LanguageType,
    selected: LanguageType,
    onUpdate: (LanguageType) -> Unit
) {
    val isSelected = type == selected

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
        )
        RadioButton(selected = isSelected, onClick = { onUpdate(type) })
    }
}
