package com.everest.data.repository

import com.everest.database.entity.meow.MeowEntity
import com.everest.database.map.MeowWithBreeds
import kotlinx.coroutines.flow.Flow

interface HomeDbRepository {
    suspend fun saveMeow(meow: MeowEntity)
    suspend fun saveMeows(meows: List<MeowEntity>)
    suspend fun getMeowById(meowId: String): Flow<MeowWithBreeds>
}
