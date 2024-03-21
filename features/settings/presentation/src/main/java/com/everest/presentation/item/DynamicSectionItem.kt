package com.everest.presentation.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.everest.theme.dimen
import com.everest.ui.R

@Composable
fun DynamicSectionItem(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onUpdate: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.dimen.base2x,
                vertical = MaterialTheme.dimen.base
            )
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = MaterialTheme.dimen.base2x),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.dynamic),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier.weight(1f)

        )
        Switch(
            checked = enabled,
            onCheckedChange = onUpdate
        )
    }
}
