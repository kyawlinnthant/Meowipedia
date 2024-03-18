package com.everest.presentation.state

import com.everest.type.ThemeType

data class SettingsViewModelState(
    val theme: ThemeType = ThemeType.System,
    val isDynamicEnabled: Boolean = true
) {
    fun asTheme() = theme
    fun asDynamic() = isDynamicEnabled
}
