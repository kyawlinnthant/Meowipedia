package com.everest.categories.data.model.categories

import com.everest.database.entity.CategoriesRemoteKeyEntity
import com.everest.database.entity.MeowEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun CategoryDTO.toEntity(): MeowEntity {
    return MeowEntity(
        id = id ?: "",
        image = image?.url ?: "",
        name = name ?: "",
        description = description?:"",
        createdAt = "${Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())}",
        activeAt = "${Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())}"
    )
}


fun CategoryDTO.toCategoriesRemoteKey(
    nextPage: Int?, prevPage: Int?, currentPage: Int
): CategoriesRemoteKeyEntity {
    return CategoriesRemoteKeyEntity(
        id = id ?: "", nextPage = nextPage, prevPage = prevPage, currentPage = currentPage
    )
}
