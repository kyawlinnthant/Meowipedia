package com.everest.domain.usecase

import com.everest.data.repository.SettingDsRepo
import com.everest.type.DayNightTheme
import com.everest.type.LanguageType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListenLanguageStatus @Inject constructor(
    private val repo: SettingDsRepo
) {
    suspend operator fun invoke(): Flow<LanguageType> {
        return repo.listenLanguage()
    }
}
