package com.everest.datastore

sealed interface DayNightTheme {
    data object Day : DayNightTheme
    data object Night : DayNightTheme
    data object System : DayNightTheme
}

private object DayNightThemeValue {
    const val DAY = 0
    const val NIGHT = 1
    const val SYSTEM = 2
}

fun DayNightTheme.value(): Int {
    return when (this) {
        DayNightTheme.Day -> DayNightThemeValue.DAY
        DayNightTheme.Night -> DayNightThemeValue.NIGHT
        DayNightTheme.System -> DayNightThemeValue.SYSTEM
    }
}

fun Int.toDayNightTheme(): DayNightTheme {
    return when (this) {
        DayNightThemeValue.DAY -> DayNightTheme.Day
        DayNightThemeValue.NIGHT -> DayNightTheme.Night
        DayNightThemeValue.SYSTEM -> DayNightTheme.System
        else -> DayNightTheme.System
    }
}
