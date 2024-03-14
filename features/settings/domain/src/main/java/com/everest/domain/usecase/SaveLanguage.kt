package com.everest.domain.usecase

import com.everest.data.repository.SettingDsRepo
import com.everest.type.LanguageType
import javax.inject.Inject

class SaveLanguage @Inject constructor(
    private val repo: SettingDsRepo
) {
    suspend operator fun invoke(languageType: LanguageType) {
        repo.saveLanguage(languageType)
    }
}
