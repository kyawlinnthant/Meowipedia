package com.everest.domain.usecase

import com.everest.data.repository.SettingDsRepo
import javax.inject.Inject

class SaveDayNightTheme @Inject constructor(
    private val repo: SettingDsRepo
) {
    suspend operator fun invoke(theme: com.everest.type.ThemeType) {
        repo.saveThemeStatus(theme)
    }
}
