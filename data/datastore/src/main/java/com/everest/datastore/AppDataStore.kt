package com.everest.datastore

import com.everest.type.DayNightTheme
import com.everest.type.LanguageType
import kotlinx.coroutines.flow.Flow

interface AppDataStore {
    suspend fun putLanguage(languageType: LanguageType)
    suspend fun putTheme(theme: DayNightTheme)
    suspend fun putEnabledDynamic(enabled: Boolean)

    fun pullTheme(): Flow<DayNightTheme>
    fun getLanguage(): Flow<LanguageType>
    fun pullEnabledDynamic(): Flow<Boolean>
}
