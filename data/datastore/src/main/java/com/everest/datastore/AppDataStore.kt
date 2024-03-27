package com.everest.datastore

import com.everest.type.DayNightTheme
import kotlinx.coroutines.flow.Flow

interface AppDataStore {
    suspend fun putTheme(theme: DayNightTheme)
    suspend fun putEnabledDynamic(enabled: Boolean)
    suspend fun pullTheme(): Flow<DayNightTheme>
    suspend fun pullEnabledDynamic(): Flow<Boolean>
}
