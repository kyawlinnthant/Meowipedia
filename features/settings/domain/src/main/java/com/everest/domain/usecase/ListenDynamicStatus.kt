package com.everest.domain.usecase

import com.everest.data.repository.SettingDsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListenDynamicStatus @Inject constructor(
    private val repo : SettingDsRepo
) {
    suspend operator fun invoke() : Flow<Boolean>{
        return repo.listenDynamicStatus()
    }
}
