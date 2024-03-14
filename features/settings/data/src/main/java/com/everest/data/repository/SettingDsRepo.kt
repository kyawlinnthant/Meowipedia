package com.everest.data.repository

import com.everest.type.DayNightTheme
import com.everest.type.LanguageType
import kotlinx.coroutines.flow.Flow

interface SettingDsRepo {
    suspend fun saveLanguage(languageType: LanguageType)
    suspend fun saveThemeStatus(theme: DayNightTheme)
    suspend fun saveDynamicStatus(isEnabled: Boolean)
    suspend fun listenThemeStatus(): Flow<DayNightTheme>

    suspend fun listenLanguage(): Flow<LanguageType>
    suspend fun listenDynamicStatus(): Flow<Boolean>
}
