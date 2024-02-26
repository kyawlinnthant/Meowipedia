package com.everest.data.model.breed

import com.everest.database.entity.breed.WeightEntity
import kotlinx.serialization.Serializable

@Serializable
data class WeightDTO(
    val imperial: String,
    val metric: String
) {
    fun toEntity() = WeightEntity(
        imperial = imperial,
        metric = metric
    )
}
