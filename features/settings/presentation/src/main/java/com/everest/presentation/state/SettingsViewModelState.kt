package com.everest.presentation.state

import com.everest.type.DayNightTheme
import com.everest.type.LanguageType

data class SettingsViewModelState(
    val theme: DayNightTheme = DayNightTheme.System,
    val languageType: LanguageType = LanguageType.en,
    val isDynamicEnabled: Boolean = true
) {
    fun asLanguage() = languageType
    fun asTheme() = theme
    fun asDynamic() = isDynamicEnabled
}
