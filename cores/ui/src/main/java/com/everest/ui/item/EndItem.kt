package com.everest.ui.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.everest.theme.MeowipediaTheme
import com.everest.theme.dimen
import com.everest.type.ThemeType

@Composable
fun EndItem(
    modifier: Modifier = Modifier,
    endCount: Int = 5
) {
    val startCount = 1
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimen.base2x),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.base),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat((startCount..endCount).count()) {
                Box(
                    modifier = modifier
                        .size(MaterialTheme.dimen.base)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                )
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    MeowipediaTheme(appTheme = ThemeType.NightType, dynamicColor = true) {
        Surface {
            EndItem()
        }
    }
}
