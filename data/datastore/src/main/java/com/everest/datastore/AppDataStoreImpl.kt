package com.everest.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import com.everest.dispatcher.DispatcherModule
import com.everest.type.ThemeType
import com.everest.type.toDayNightTheme
import com.everest.type.value
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class AppDataStoreImpl @Inject constructor(
    private val ds: DataStore<Preferences>,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : AppDataStore {

    companion object {
        const val PREF_NAME = "ds.pref"
        val THEME = intPreferencesKey("com.everest.theme")
        val DYNAMIC = booleanPreferencesKey("com.everest.dynamic")
    }

    override suspend fun putTheme(theme: ThemeType) {
        ds.edit {
            it[THEME] = theme.value()
        }
    }

    override suspend fun putEnabledDynamic(enabled: Boolean) {
        ds.edit {
            it[DYNAMIC] = enabled
        }
    }

    override fun pullTheme(): Flow<ThemeType> {
        return ds.data.catch { e ->
            if (e is IOException) emit(emptyPreferences()) else throw e
        }.map { pref ->
            pref[THEME]?.toDayNightTheme() ?: ThemeType.System
        }.flowOn(io)
    }

    override fun pullEnabledDynamic(): Flow<Boolean> {
        return ds.data.catch { e ->
            if (e is IOException) emit(emptyPreferences()) else throw e
        }.map { pref ->
            pref[DYNAMIC] ?: true
        }.flowOn(io)
    }
}
