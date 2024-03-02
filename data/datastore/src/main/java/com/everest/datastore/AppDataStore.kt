package com.everest.datastore

import kotlinx.coroutines.flow.Flow

interface AppDataStore {
    suspend fun putTheme(theme: com.everest.type.DayNightTheme)
    suspend fun putEnabledDynamic(enabled: Boolean)

    suspend fun pullTheme(): Flow<com.everest.type.DayNightTheme>
    suspend fun pullEnabledDynamic(): Flow<Boolean>
}
