package com.everest.type

sealed interface ThemeType {
    data object DayType : ThemeType
    data object NightType : ThemeType
    data object System : ThemeType
}

private object DayNightThemeValue {
    const val DAY = 0
    const val NIGHT = 1
    const val SYSTEM = 2
}

fun ThemeType.value(): Int {
    return when (this) {
        ThemeType.DayType -> DayNightThemeValue.DAY
        ThemeType.NightType -> DayNightThemeValue.NIGHT
        ThemeType.System -> DayNightThemeValue.SYSTEM
    }
}

fun Int.toDayNightTheme(): ThemeType {
    return when (this) {
        DayNightThemeValue.DAY -> ThemeType.DayType
        DayNightThemeValue.NIGHT -> ThemeType.NightType
        DayNightThemeValue.SYSTEM -> ThemeType.System
        else -> ThemeType.System
    }
}
