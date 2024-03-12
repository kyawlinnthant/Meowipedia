package com.everest.presentation.state

import com.everest.type.DayNightTheme


data class SettingsViewModelState(
    val theme: DayNightTheme = DayNightTheme.System,
    val isDynamicEnabled: Boolean = true
) {
    fun asTheme() = theme
    fun asDynamic() = isDynamicEnabled
}
