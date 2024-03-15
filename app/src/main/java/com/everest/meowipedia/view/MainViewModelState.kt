package com.everest.meowipedia.view

import com.everest.type.DayNightTheme

data class MainViewModelState(
    val theme: DayNightTheme = DayNightTheme.System,
    val dynamic: Boolean = true
) {
    fun asTheme() = theme
    fun asDynamic() = dynamic
}
