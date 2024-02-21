package com.everest.categories.data.repository

import com.everest.database.entity.CategoryEntity

interface MeowRepo {
    suspend fun saveMeow(categoryEntity: CategoryEntity)
}
