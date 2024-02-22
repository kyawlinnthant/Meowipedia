package com.everest.categories.data.repository

import androidx.paging.Pager
import com.everest.categories.data.model.categories.CategoryDTO
import com.everest.database.entity.CategoryEntity
import com.everest.database.entity.MeowEntity
import com.everest.util.result.DataResult

interface CategoriesRepo {
    fun getCategories(): Pager<Int, CategoryEntity>

    suspend fun searchCategories(keyword: String): DataResult<List<CategoryDTO>>
}
