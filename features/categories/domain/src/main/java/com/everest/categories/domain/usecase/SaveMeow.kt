package com.everest.categories.domain.usecase

import com.everest.categories.data.model.categories.vo.CategoryVO
import com.everest.categories.data.repository.MeowRepo
import com.everest.database.entity.MeowEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

class SaveMeow @Inject constructor(
    private val meowRepo: MeowRepo
) {
    suspend operator fun invoke(categoryVO: CategoryVO) {
        meowRepo.saveMeow(
            meowEntity = MeowEntity(
                id = categoryVO.id,
                image = categoryVO.image,
                name = categoryVO.name,
                description = "categoryVO",
                activeAt = "12313",
                createdAt = "${Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())}"
            )
        )
    }
}
