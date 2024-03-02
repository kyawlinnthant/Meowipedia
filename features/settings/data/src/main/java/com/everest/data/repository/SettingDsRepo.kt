package com.everest.data.repository

import com.everest.type.DayNightTheme
import kotlinx.coroutines.flow.Flow

interface SettingDsRepo {
    suspend fun saveThemeStatus(theme: com.everest.type.DayNightTheme)
    suspend fun saveDynamicStatus(isEnabled: Boolean)
    suspend fun listenThemeStatus(): Flow<com.everest.type.DayNightTheme>
    suspend fun listenDynamicStatus(): Flow<Boolean>
}
