package com.everest.domain.usecase

import com.everest.data.repository.HomeDbRepository
import com.everest.database.entity.MeowEntity
import com.everest.domain.model.categories.CategoryVO
import javax.inject.Inject

class SaveMeow @Inject constructor(
    private val dbRepo: HomeDbRepository
) {
    suspend operator fun invoke(categoryVO: CategoryVO) {
        dbRepo.saveMeow(
            meowEntity = MeowEntity(
                id = "",
                width = 100,
                url = "testing_url",
                height = 100
            )
//            categoryEntity = CategoryEntity(
//                id = categoryVO.id,
//                image = categoryVO.image,
//                name = categoryVO.name,
//                description = "categoryVO",
//                activeAt = "12313",
//                createdAt = "${Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())}"
//            )
        )
    }
}
