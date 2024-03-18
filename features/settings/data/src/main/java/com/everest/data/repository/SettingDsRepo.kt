package com.everest.data.repository

import com.everest.type.ThemeType
import kotlinx.coroutines.flow.Flow

interface SettingDsRepo {
    suspend fun saveThemeStatus(theme: ThemeType)
    suspend fun saveDynamicStatus(isEnabled: Boolean)
    fun listenThemeStatus(): Flow<ThemeType>

    fun listenDynamicStatus(): Flow<Boolean>
}
