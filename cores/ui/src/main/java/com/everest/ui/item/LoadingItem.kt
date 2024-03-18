package com.everest.ui.item

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.KeyframesSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.everest.theme.MeowipediaTheme
import com.everest.theme.dimen
import com.everest.type.ThemeType
import kotlinx.coroutines.delay

@Composable
fun LoadingItem(
    modifier: Modifier = Modifier,
    text: String = "LOADING",
    spaceBetween: Dp = 20.dp,
    travelDistance: Dp = 30.dp,
    delayBetween: Long = 100L,
    durationMilli: Int = 500,
    keyframe: KeyframesSpec<Float> = keyframes {
        durationMillis = durationMilli
        0.0f at 0 using LinearOutSlowInEasing
    }
) {
    val distance = with(LocalDensity.current) { travelDistance.toPx() }
    val descriptions = text.toCharArray()
    val charMap = descriptions.associateWith {
        remember { Animatable(initialValue = 0f) }
    }

    charMap.values.forEachIndexed { index, animated ->
        LaunchedEffect(key1 = animated) {
            delay(index * delayBetween)
            animated.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframe,
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimen.base)
            .padding(top = travelDistance),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spaceBetween)
        ) {
            charMap.forEach { (char, anim) ->
                Text(
                    text = "$char",
                    modifier = modifier.graphicsLayer {
                        translationY = -anim.value * distance
                    },
                    style = MaterialTheme.typography.labelLarge
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
            LoadingItem()
        }
    }
}
