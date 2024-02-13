package com.everest.categories.data.repository

import com.everest.categories.data.model.categories.CategoryDTO
import com.everest.util.result.DataResult

interface CategoriesRepo {
    suspend fun fetchCategories(): DataResult<List<CategoryDTO>>
    suspend fun searchCategories(keyword: String): DataResult<List<CategoryDTO>>
}