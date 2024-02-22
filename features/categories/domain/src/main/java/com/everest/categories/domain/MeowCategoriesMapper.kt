package com.everest.categories.domain

import com.everest.categories.data.model.categories.CategoryDTO
import com.everest.categories.domain.vo.CategoryVO
import com.everest.database.entity.CategoryEntity
import com.everest.database.entity.CategoryKeyEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun CategoryDTO.toCategoriesEntity(): CategoryEntity {
    return CategoryEntity(
        id = id ?: "",
        image = image?.url ?: "",
        name = name ?: "",
        description = description ?: "",
        createdAt = "${Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())}",
        activeAt = "${Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())}"
    )
}


fun CategoryEntity.toVo() = CategoryVO(
    id = id,
    name = name,
    image = image,
)

fun CategoryDTO.toCategoriesRemoteKey(
    nextPage: Int?, prevPage: Int?, currentPage: Int
): CategoryKeyEntity {
    return CategoryKeyEntity(
        categoryId = id ?: "", nextPage = nextPage, prevPage = prevPage, currentPage = currentPage
    )
}
