package com.everest.domain.model.meow

import com.everest.database.entity.meow.MeowEntity

fun MeowEntity.toVo() = MeowVo(
    id = id,
    photo = url,
    breedId = breedId,
    width = width,
    height = height
)
