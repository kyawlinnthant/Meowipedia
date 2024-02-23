package com.everest.database.map

import androidx.room.Embedded
import androidx.room.Relation
import com.everest.database.entity.breed.BreedEntity
import com.everest.database.entity.meow.MeowEntity

data class BreedWithMeows(
    @Embedded
    val breed: BreedEntity,

    @Relation(
        parentColumn = BreedEntity.BREED_ID,
        entityColumn = MeowEntity.BREED_ID
    )
    val meows: List<MeowEntity> = emptyList()
)
