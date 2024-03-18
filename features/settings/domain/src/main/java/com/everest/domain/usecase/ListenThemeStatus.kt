package com.everest.domain.usecase

import com.everest.data.repository.SettingDsRepo
import com.everest.type.ThemeType
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ListenThemeStatus @Inject constructor(
    private val repo: SettingDsRepo
) {
    suspend operator fun invoke(): Flow<ThemeType> {
        return repo.listenThemeStatus()
    }
}
