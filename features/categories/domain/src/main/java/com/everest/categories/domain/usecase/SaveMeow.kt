package com.everest.categories.domain.usecase

import com.everest.categories.data.repository.MeowRepo
import com.everest.categories.domain.vo.CategoryVO
import com.everest.database.entity.MeowEntity
import javax.inject.Inject
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

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
