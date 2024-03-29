package com.everest.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import com.everest.dispatcher.DispatcherModule
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AppDataStoreImpl @Inject constructor(
    private val ds: DataStore<Preferences>,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : AppDataStore {

    companion object {
        const val PREF_NAME = "ds.pref"
        val THEME = intPreferencesKey("com.everest.theme")
        val DYNAMIC = booleanPreferencesKey("com.everest.dynamic")
    }

    override suspend fun putTheme(theme: DayNightTheme) {
        withContext(io) {
            ds.edit {
                it[THEME] = theme.value()
            }
        }
    }

    override suspend fun putEnabledDynamic(enabled: Boolean) {
        withContext(io) {
            ds.edit {
                it[DYNAMIC] = enabled
            }
        }
    }

    override suspend fun pullTheme(): Flow<DayNightTheme> {
        return ds.data.catch { e ->
            if (e is IOException) emit(emptyPreferences()) else throw e
        }.map { pref ->
            pref[THEME]?.toDayNightTheme() ?: DayNightTheme.System
        }.flowOn(io)
    }

    override suspend fun pullEnabledDynamic(): Flow<Boolean> {
        return ds.data.catch { e ->
            if (e is IOException) emit(emptyPreferences()) else throw e
        }.map { pref ->
            pref[DYNAMIC] ?: true
        }.flowOn(io)
    }
}
