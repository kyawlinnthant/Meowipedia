package com.everest.database.map

import androidx.room.Embedded
import androidx.room.Relation
import com.everest.database.entity.breed.BreedEntity
import com.everest.database.entity.meow.MeowEntity

data class MeowWithBreeds(
    @Embedded
    val meow: MeowEntity,

    @Relation(
        parentColumn = MeowEntity.BREED_ID,
        entityColumn = BreedEntity.BREED_ID
    )
    val breeds: List<BreedEntity> = emptyList()
)
