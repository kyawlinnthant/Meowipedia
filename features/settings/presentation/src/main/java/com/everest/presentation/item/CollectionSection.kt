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
import com.everest.theme.dimen
import com.everest.ui.R

@Composable
fun CollectionSection(
    modifier: Modifier = Modifier,
    onAction: () -> Unit
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
            text = stringResource(id = R.string.news_feed),
            modifier = modifier
                .weight(1f)
                .padding(
                    top = MaterialTheme.dimen.standard,
                    bottom = MaterialTheme.dimen.standard,
                    start = MaterialTheme.dimen.base2x
                ),
            style = MaterialTheme.typography.titleMedium
        )
        Icon(
            modifier = modifier.padding(
                top = MaterialTheme.dimen.base,
                bottom = MaterialTheme.dimen.base,
                end = MaterialTheme.dimen.standard * 2
            ),
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "click"
        )
    }
}
