package com.everest.ui.screen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.everest.theme.MeowipediaTheme
import com.everest.theme.dimen
import com.everest.type.ThemeType
import com.everest.ui.R
import com.everest.util.result.NetworkError

@Composable
fun FullScreenErrorView(
    modifier: Modifier = Modifier,
    type: NetworkError,
    onRetry: () -> Unit
) {
    val description = when (type) {
        is NetworkError.Dynamic -> type.message
        NetworkError.NoInternet -> stringResource(id = R.string.no_internet)
        NetworkError.SomethingWrong -> stringResource(id = R.string.something_wrong)
    }

    val icon = when (type) {
        is NetworkError.Dynamic -> Icons.Outlined.Lock
        NetworkError.NoInternet -> Icons.Outlined.Notifications
        NetworkError.SomethingWrong -> Icons.Outlined.Warning
    }

    val value by rememberInfiniteTransition(label = "transition").animateFloat(
        initialValue = 25f,
        targetValue = -25f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "repeat"
    )
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = modifier
                .size(MaterialTheme.dimen.descriptionIcon)
                .graphicsLayer(
                    transformOrigin = TransformOrigin(
                        pivotFractionX = 0.5f,
                        pivotFractionY = 0.0f
                    ),
                    rotationZ = value
                )
        )
        Spacer(modifier = modifier.height(MaterialTheme.dimen.base2x))
        Text(text = description, style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = modifier.height(MaterialTheme.dimen.base2x))
        OutlinedButton(onClick = onRetry) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
@Preview
private fun Preview() {
    MeowipediaTheme(appTheme = ThemeType.NightType, dynamicColor = true) {
        Surface {
            FullScreenErrorView(type = NetworkError.NoInternet) {
            }
        }
    }
}
