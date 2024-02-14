package com.everest.data.repository

import com.everest.datastore.AppDataStore
import com.everest.datastore.DayNightTheme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingDsRepoImpl @Inject constructor(
    private val ds : AppDataStore
) : SettingDsRepo{
    override suspend fun saveThemeStatus(theme: DayNightTheme) {
        ds.putTheme(theme)
    }

    override suspend fun saveDynamicStatus(isEnabled: Boolean) {
        ds.putEnabledDynamic(isEnabled)
    }

    override suspend fun listenThemeStatus(): Flow<DayNightTheme> {
        return ds.pullTheme()
    }

    override suspend fun listenDynamicStatus(): Flow<Boolean> {
        return ds.pullEnabledDynamic()
    }
}
