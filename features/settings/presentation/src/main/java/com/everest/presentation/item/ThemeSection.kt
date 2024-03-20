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
import com.everest.type.ThemeType
import com.everest.ui.R

@Composable
fun ThemeSection(
    modifier: Modifier = Modifier,
    selected: ThemeType,
    onUpdate: (ThemeType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (expanded) 180f else 0f, label = "")

    val selectedTheme = when (selected) {
        ThemeType.DayType -> stringResource(id = R.string.light)
        ThemeType.NightType -> stringResource(id = R.string.dark)
        ThemeType.System -> stringResource(id = R.string.system)
    }

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
                text = stringResource(id = R.string.theme),
                modifier = modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = selectedTheme, style = MaterialTheme.typography.labelSmall)
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
            ThemePicker(selected = selected, onUpdate = onUpdate)
        }
    }
}

@Composable
fun ThemePicker(
    modifier: Modifier = Modifier,
    selected: ThemeType,
    onUpdate: (ThemeType) -> Unit
) {
    Column(
        modifier = modifier.padding(vertical = MaterialTheme.dimen.base)
    ) {
        ThemeSectionItem(
            text = stringResource(id = R.string.light_mode),
            type = ThemeType.DayType,
            selected = selected,
            onUpdate = onUpdate
        )
        ThemeSectionItem(
            text = stringResource(id = R.string.dark_mode),
            type = ThemeType.NightType,
            selected = selected,
            onUpdate = onUpdate
        )
        ThemeSectionItem(
            text = stringResource(id = R.string.system_default),
            type = ThemeType.System,
            selected = selected,
            onUpdate = onUpdate
        )
    }
}
