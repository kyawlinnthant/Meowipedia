package com.everest.data.repository

import com.everest.datastore.AppDataStore
import com.everest.type.ThemeType
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SettingDsRepoImpl @Inject constructor(
    private val ds: AppDataStore
) : SettingDsRepo {

    override suspend fun saveThemeStatus(theme: ThemeType) {
        ds.putTheme(theme)
    }

    override suspend fun saveDynamicStatus(isEnabled: Boolean) {
        ds.putEnabledDynamic(isEnabled)
    }

    override fun listenThemeStatus(): Flow<ThemeType> {
        return ds.pullTheme()
    }

    override fun listenDynamicStatus(): Flow<Boolean> {
        return ds.pullEnabledDynamic()
    }
}
