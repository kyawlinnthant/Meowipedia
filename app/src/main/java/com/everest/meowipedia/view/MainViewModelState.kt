package com.everest.meowipedia.view

import com.everest.datastore.DayNightTheme

data class MainViewModelState(
    val theme: DayNightTheme = DayNightTheme.System,
    val dynamic: Boolean = true
) {
    fun asTheme() = theme
    fun asDynamic() = dynamic
}
