package com.everest.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp

val fonts = FontFamily(
    Font(R.font.google_sans_bold, weight = FontWeight.Black),
    Font(R.font.google_sans_medium, weight = FontWeight.Medium),
    Font(R.font.google_sans_regular, weight = FontWeight.Normal),
    Font(R.font.google_sans_italic, weight = FontWeight.Thin),
    Font(R.font.google_sans_bold_italic, weight = FontWeight.Bold),
    Font(R.font.google_sans_medium_italic, weight = FontWeight.Medium)
)

val MeowsTypo = Typography(
    displayLarge = TextStyle(
        fontFamily = fonts,
        lineHeight = 64.sp,
        fontSize = 57.sp
    ),
    displayMedium = TextStyle(
        fontFamily = fonts,
        lineHeight = 52.sp,
        fontSize = 45.sp
    ),
    displaySmall = TextStyle(
        fontFamily = fonts,
        lineHeight = 34.sp,
        fontSize = 28.sp,
        fontWeight = FontWeight(weight = 800),
        letterSpacing = TextUnit(
            value = -0.5f,
            type = TextUnitType.Sp
        )
    ),
    headlineLarge = TextStyle(
        fontFamily = fonts,
        lineHeight = 40.sp,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = fonts,
        lineHeight = 36.sp,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = fonts,
        lineHeight = 32.sp,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fonts,
        lineHeight = 41.sp,
        fontSize = 34.sp,
        fontWeight = FontWeight(weight = 800),
        letterSpacing = TextUnit(
            value = -1f,
            type = TextUnitType.Sp
        )
    ),
    titleMedium = TextStyle(
        fontFamily = fonts,
        lineHeight = 24.sp,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = fonts,
        lineHeight = 18.sp,
        fontSize = 13.sp,
        fontWeight = FontWeight(weight = 800),
        letterSpacing = TextUnit(
            value = -0.08f,
            type = TextUnitType.Sp
        )
    ),
    bodyLarge = TextStyle(
        fontFamily = fonts,
        lineHeight = 24.sp,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fonts,
        lineHeight = 22.sp,
        fontSize = 17.sp,
        fontWeight = FontWeight(weight = 400),
        letterSpacing = TextUnit(
            value = -0.41f,
            type = TextUnitType.Sp
        )
    ),
    bodySmall = TextStyle(
        fontFamily = fonts,
        lineHeight = 20.sp,
        fontSize = 15.sp,
        fontWeight = FontWeight(weight = 400),
        letterSpacing = TextUnit(
            value = -0.24f,
            type = TextUnitType.Sp
        )
    ),
    labelLarge = TextStyle(
        fontFamily = fonts,
        lineHeight = 20.sp,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = fonts,
        lineHeight = 16.sp,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fonts,
        lineHeight = 16.sp,
        fontSize = 11.sp
    )
)
