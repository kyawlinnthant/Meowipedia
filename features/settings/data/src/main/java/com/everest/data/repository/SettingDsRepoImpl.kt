package com.everest.data.repository

import com.everest.datastore.AppDataStore
import com.everest.type.DayNightTheme
import com.everest.type.LanguageType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingDsRepoImpl @Inject constructor(
    private val ds: AppDataStore
) : SettingDsRepo {
    override suspend fun saveLanguage(languageType: LanguageType) {
        ds.putLanguage(languageType)
    }

    override suspend fun saveThemeStatus(theme: DayNightTheme) {
        ds.putTheme(theme)
    }

    override suspend fun saveDynamicStatus(isEnabled: Boolean) {
        ds.putEnabledDynamic(isEnabled)
    }

    override suspend fun listenThemeStatus(): Flow<DayNightTheme> {
        return ds.pullTheme()
    }

    override suspend fun listenLanguage(): Flow<LanguageType> {
        return ds.getLanguage()
    }

    override suspend fun listenDynamicStatus(): Flow<Boolean> {
        return ds.pullEnabledDynamic()
    }
}
