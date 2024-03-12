package com.everest.data.repository

import com.everest.datastore.AppDataStore
import com.everest.type.DayNightTheme
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SettingDsRepoImpl @Inject constructor(
    private val ds: AppDataStore
) : SettingDsRepo {
    override suspend fun saveThemeStatus(theme: com.everest.type.DayNightTheme) {
        ds.putTheme(theme)
    }

    override suspend fun saveDynamicStatus(isEnabled: Boolean) {
        ds.putEnabledDynamic(isEnabled)
    }

    override suspend fun listenThemeStatus(): Flow<com.everest.type.DayNightTheme> {
        return ds.pullTheme()
    }

    override suspend fun listenDynamicStatus(): Flow<Boolean> {
        return ds.pullEnabledDynamic()
    }
}
