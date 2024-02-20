package com.everest.domain.usecase

import com.everest.data.repository.SettingDsRepo
import javax.inject.Inject

class SaveDynamic @Inject constructor(
    private val repo: SettingDsRepo
) {
    suspend operator fun invoke(enabled: Boolean) {
        repo.saveDynamicStatus(enabled)
    }
}
