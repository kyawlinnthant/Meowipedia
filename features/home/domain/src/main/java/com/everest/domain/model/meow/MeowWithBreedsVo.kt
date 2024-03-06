package com.everest.domain.model.meow

import com.everest.domain.model.categories.breed.BreedVo

data class MeowWithBreedsVo(
    val id: String = "",
    val photo: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val breeds: List<BreedVo> = emptyList()
)

