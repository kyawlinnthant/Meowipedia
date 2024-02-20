package com.everest.domain.usecase

import com.everest.data.repository.SettingDsRepo
import com.everest.datastore.DayNightTheme
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ListenThemeStatus @Inject constructor(
    private val repo: SettingDsRepo
) {
    suspend operator fun invoke(): Flow<DayNightTheme> {
        return repo.listenThemeStatus()
    }
}
