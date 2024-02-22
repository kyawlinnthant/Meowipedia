package com.everest.categories.data.repository

import com.everest.database.entity.CategoryEntity
import com.everest.database.entity.MeowEntity

interface MeowRepo {
    suspend fun saveMeow(meowEntity: MeowEntity)
}
