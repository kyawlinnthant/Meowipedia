package com.everest.domain.usecase

import javax.inject.Inject

data class SettingsViewModelUseCase @Inject constructor(
    val saveTheme: SaveDayNightTheme,
    val saveDynamic: SaveDynamic,
    val saveLanguage: SaveLanguage,
    val listenTheme: ListenThemeStatus,
    val listenDynamic: ListenDynamicStatus,
    val listenLanguage: ListenLanguageStatus
)
