package com.everest.data.model.meow

import com.everest.data.model.breed.BreedDTO
import com.everest.database.entity.meow.MeowEntity
import com.everest.database.entity.meow.MeowKeyEntity
import kotlin.random.Random
import kotlinx.serialization.Serializable

@Serializable
data class MeowDTO(
    val id: String,
    val height: Int,
    val url: String,
    val width: Int,
    val breeds: List<BreedDTO>
) {
    fun toEntity(isForPaging: Boolean = false) = MeowEntity(
        id = id,
        height = height,
        width = width,
        url = url,
        isForPaging = isForPaging,
        breedId = breeds.firstOrNull()?.id ?: Random.nextInt().toString()
    )

    fun toKey(
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
