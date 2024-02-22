package com.everest.domain.model.gallery

import com.everest.database.entity.MeowEntity

fun MeowEntity.toVo() = Gallery(
    id = id,
    photo = url
)
