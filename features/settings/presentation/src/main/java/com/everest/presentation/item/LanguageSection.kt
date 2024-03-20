package com.everest.presentation.item

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.everest.theme.dimen
import com.everest.type.LanguageType
import com.everest.ui.R

@Composable
fun LanguageSection(
    modifier: Modifier = Modifier,
    selected: LanguageType,
    onUpdate: (LanguageType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (expanded) 180f else 0f, label = "")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .padding(
                horizontal = MaterialTheme.dimen.base2x,
                vertical = MaterialTheme.dimen.base
            )
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = MaterialTheme.dimen.base2x)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.language),
                modifier = modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(id = R.string.select_language),
                style = MaterialTheme.typography.labelSmall
            )
            IconButton(
                onClick = {
                    expanded = !expanded
                },
                modifier = modifier.rotate(rotationState)
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_arrow_down), contentDescription = "click")
            }
        }
        if (expanded) {
            LanguagePicker(selected = selected, onUpdate = onUpdate)
        }
    }
}

@Composable
fun LanguagePicker(
    modifier: Modifier = Modifier,
    selected: LanguageType,
    onUpdate: (LanguageType) -> Unit
) {
    Column(
        modifier = modifier.padding(vertical = MaterialTheme.dimen.base)
    ) {
        LanguageSectionItem(
            text = stringResource(id = R.string.english),
            type = LanguageType.EN,
            selected = selected,
            onUpdate = onUpdate
        )
        LanguageSectionItem(
            text = stringResource(id = R.string.french),
            type = LanguageType.FR,
            selected = selected,
            onUpdate = onUpdate
        )
    }
}
