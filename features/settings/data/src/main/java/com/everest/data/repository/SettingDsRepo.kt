package com.everest.data.repository

import com.everest.type.DayNightTheme
import kotlinx.coroutines.flow.Flow

interface SettingDsRepo {
    suspend fun saveThemeStatus(theme: DayNightTheme)
    suspend fun saveDynamicStatus(isEnabled: Boolean)
    fun listenThemeStatus(): Flow<DayNightTheme>

    fun listenDynamicStatus(): Flow<Boolean>
}
