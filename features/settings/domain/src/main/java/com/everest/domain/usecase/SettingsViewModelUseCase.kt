package com.everest.domain.usecase

import javax.inject.Inject

data class SettingsViewModelUseCase @Inject constructor(
    val saveTheme: SaveDayNightTheme,
    val saveDynamic: SaveDynamic,
    val listenTheme: ListenThemeStatus,
    val listenDynamic: ListenDynamicStatus
)
