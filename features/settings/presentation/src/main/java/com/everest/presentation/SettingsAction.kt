package com.everest.presentation

import com.everest.datastore.DayNightTheme

sealed interface SettingsAction {
    data class UpdateTheme(val theme: DayNightTheme) : SettingsAction
    data class UpdateDynamic(val enabled: Boolean) : SettingsAction
    object OnBackPress : SettingsAction
}
