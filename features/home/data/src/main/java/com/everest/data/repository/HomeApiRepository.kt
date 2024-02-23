package com.everest.data.repository

import androidx.paging.Pager
import com.everest.data.model.breed.BreedDTO
import com.everest.database.entity.breed.BreedEntity
import com.everest.database.entity.meow.MeowEntity
import com.everest.util.result.DataResult

interface HomeApiRepository {
    fun getBreeds(): Pager<Int, BreedEntity>
    suspend fun searchBreeds(keyword: String): DataResult<List<BreedDTO>>
    fun getMeows(): Pager<Int, MeowEntity>
}
