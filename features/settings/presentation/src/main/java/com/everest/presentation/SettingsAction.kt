package com.everest.presentation

import com.everest.type.DayNightTheme
import com.everest.type.LanguageType

sealed interface SettingsAction {
    data class UpdateLanguage(val languageType: LanguageType) : SettingsAction
    data class UpdateTheme(val theme: DayNightTheme) : SettingsAction
    data class UpdateDynamic(val enabled: Boolean) : SettingsAction
    data object OnBackPress : SettingsAction
}
