package com.everest.meowipedia.view

import com.everest.type.DayNightTheme
import com.everest.type.LanguageType

data class MainViewModelState(
    val theme: DayNightTheme = DayNightTheme.System,
    val language: LanguageType = LanguageType.en,
    val dynamic: Boolean = true
) {
    fun asLanguage() = language
    fun asTheme() = theme
    fun asDynamic() = dynamic
}
