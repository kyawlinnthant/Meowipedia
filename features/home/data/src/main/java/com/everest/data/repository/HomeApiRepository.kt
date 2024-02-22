package com.everest.data.repository

import androidx.paging.Pager
import com.everest.data.model.categories.CategoryDTO
import com.everest.database.entity.CategoryEntity
import com.everest.database.entity.MeowEntity
import com.everest.util.result.DataResult

interface HomeApiRepository {
    fun getCategories(): Pager<Int, CategoryEntity>
    suspend fun searchCategories(keyword: String): DataResult<List<CategoryDTO>>
    fun getGalleries(): Pager<Int, MeowEntity>
}
