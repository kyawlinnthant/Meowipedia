package com.everest.domain.usecase

import com.everest.data.repository.SettingDsRepo
import com.everest.datastore.DayNightTheme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListenThemeStatus @Inject constructor(
    private val repo: SettingDsRepo
) {
    suspend operator fun invoke(): Flow<DayNightTheme> {
        return repo.listenThemeStatus()
    }
}
