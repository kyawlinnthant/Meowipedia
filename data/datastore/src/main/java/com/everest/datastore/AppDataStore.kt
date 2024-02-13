package com.everest.datastore

import kotlinx.coroutines.flow.Flow

interface AppDataStore {
    suspend fun putTheme(theme: DayNightTheme)
    suspend fun putEnabledDynamic(enabled: Boolean)

    suspend fun pullTheme(): Flow<DayNightTheme>
    suspend fun pullEnabledDynamic(): Flow<Boolean>
}
