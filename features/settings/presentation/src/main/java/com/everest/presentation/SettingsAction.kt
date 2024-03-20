package com.everest.presentation

import com.everest.type.ThemeType

sealed interface SettingsAction {
    data class UpdateTheme(val theme: ThemeType) : SettingsAction
    data class UpdateDynamic(val enabled: Boolean) : SettingsAction
    data class Navigate(val route: String) : SettingsAction
    data object OnBackPress : SettingsAction
    data object Logout : SettingsAction
}
