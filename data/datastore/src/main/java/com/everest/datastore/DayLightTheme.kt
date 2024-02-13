package com.everest.datastore

sealed interface DayNightTheme {
    data class Day(val status: Int = DayNightThemeValue.DAY) : DayNightTheme
    data class Night(val status: Int = DayNightThemeValue.NIGHT) : DayNightTheme
    data class System(val status: Int = DayNightThemeValue.SYSTEM) : DayNightTheme
}

private object DayNightThemeValue {
    const val DAY = 0
    const val NIGHT = 1
    const val SYSTEM = 2
}

fun Int.toDayNightTheme(): DayNightTheme {
    return when (this) {
        DayNightThemeValue.DAY -> DayNightTheme.Day()
        DayNightThemeValue.NIGHT -> DayNightTheme.Night()
        DayNightThemeValue.SYSTEM -> DayNightTheme.System()
        else -> DayNightTheme.System()
    }
}
