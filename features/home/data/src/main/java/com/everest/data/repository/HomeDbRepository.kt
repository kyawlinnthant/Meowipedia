package com.everest.data.repository

import com.everest.database.entity.MeowEntity

interface HomeDbRepository {
    suspend fun saveMeow(meowEntity: MeowEntity)
}
