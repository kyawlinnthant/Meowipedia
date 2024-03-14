package com.everest.presentation

import com.everest.type.DayNightTheme

sealed interface SettingsAction {
    data class UpdateTheme(val theme: DayNightTheme) : SettingsAction
    data class UpdateDynamic(val enabled: Boolean) : SettingsAction
    data object OnBackPress : SettingsAction
}
