package com.everest.domain.usecase

import com.everest.domain.usecase.ListenDynamicStatus
import com.everest.domain.usecase.ListenThemeStatus
import com.everest.domain.usecase.SaveDayNightTheme
import com.everest.domain.usecase.SaveDynamic
import javax.inject.Inject

data class SettingsViewModelUseCase @Inject constructor(
    val saveTheme: SaveDayNightTheme,
    val saveDynamic: SaveDynamic,
    val listenTheme: ListenThemeStatus,
    val listenDynamic: ListenDynamicStatus
)
