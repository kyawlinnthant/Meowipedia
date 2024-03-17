package com.everest.presentation.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.everest.settings.presentation.R
import com.everest.theme.dimen

@Composable
fun CollectionSection(
    modifier: Modifier = Modifier,
    onAction: () -> Unit,
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
            .clickable {
                onAction()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.collection),
            modifier = modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium
        )
        Icon(painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24), contentDescription = "click")
    }

}

