package com.everest.data.model.gallery

import com.everest.database.entity.MeowEntity
import com.everest.database.entity.MeowKeyEntity
import kotlinx.serialization.Serializable

@Serializable
data class MeowDTO(
    val id: String,
    val height: Int,
    val url: String,
    val width: Int
) {
    fun toMeowEntity() = MeowEntity(
        id = id,
        height = height,
        width = width,
        url = url
    )

    fun toKeyEntity(
        next: Int?,
        prev: Int?,
        current: Int
    ) = MeowKeyEntity(
        id = id,
        nextPage = next,
        prevPage = prev,
        currentPage = current
    )
}
