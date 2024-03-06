package com.everest.domain.model.meow

import com.everest.database.entity.meow.MeowEntity
import com.everest.database.map.MeowWithBreeds
import com.everest.domain.model.categories.breed.toVo

fun MeowEntity.toVo() = MeowVo(
    id = id,
    photo = url,
    breedId = breedId,
    width = width,
    height = height
)

fun MeowWithBreeds.toVo() = MeowWithBreedsVo(
    id = meow.id,
    photo = meow.url,
    width = meow.width,
    height = meow.height,
    breeds = breeds.map {
        it.toVo()
    }
)
