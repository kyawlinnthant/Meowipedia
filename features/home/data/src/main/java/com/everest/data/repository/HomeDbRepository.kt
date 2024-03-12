package com.everest.data.repository

import com.everest.database.entity.meow.MeowEntity

interface HomeDbRepository {
    suspend fun saveMeows(meows: List<MeowEntity>)
}
