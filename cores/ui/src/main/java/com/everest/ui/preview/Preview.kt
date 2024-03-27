package com.everest.ui.preview

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.everest.theme.MeowipediaTheme
import com.everest.type.DayNightTheme

@Composable
fun LightModePreview(
    content: @Composable () -> Unit
) {
    MeowipediaTheme(appTheme = DayNightTheme.Day, dynamicColor = false) {
        Surface {
            content()
        }
    }
}

@Composable
fun DynamicLightModePreview(
    content: @Composable () -> Unit
) {
    MeowipediaTheme(appTheme = DayNightTheme.Day, dynamicColor = true) {
        Surface {
            content()
        }
    }
}

@Composable
fun NightModePreview(
    content: @Composable () -> Unit
) {
    MeowipediaTheme(appTheme = DayNightTheme.Night, dynamicColor = false) {
        Surface {
            content()
        }
    }
}

@Composable
fun DynamicNightModePreview(
    content: @Composable () -> Unit
) {
    MeowipediaTheme(appTheme = DayNightTheme.Night, dynamicColor = true) {
        Surface {
            content()
        }
    }
}
