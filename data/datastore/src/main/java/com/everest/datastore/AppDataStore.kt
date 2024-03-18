package com.everest.datastore

import com.everest.type.ThemeType
import kotlinx.coroutines.flow.Flow

interface AppDataStore {
    suspend fun putTheme(theme: ThemeType)
    suspend fun putEnabledDynamic(enabled: Boolean)

    fun pullTheme(): Flow<ThemeType>
    fun pullEnabledDynamic(): Flow<Boolean>
}
