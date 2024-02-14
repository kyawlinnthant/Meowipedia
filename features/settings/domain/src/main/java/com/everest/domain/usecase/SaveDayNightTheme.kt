package com.everest.domain.usecase

import com.everest.data.repository.SettingDsRepo
import com.everest.datastore.DayNightTheme
import javax.inject.Inject

class SaveDayNightTheme @Inject constructor(
    private val repo : SettingDsRepo
) {
    suspend operator fun invoke(theme: DayNightTheme){
        repo.saveThemeStatus(theme)
    }
}
