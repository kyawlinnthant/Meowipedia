package com.everest.domain.usecase

import com.everest.data.repository.SettingDsRepo
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ListenDynamicStatus @Inject constructor(
    private val repo: SettingDsRepo
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return repo.listenDynamicStatus()
    }
}
