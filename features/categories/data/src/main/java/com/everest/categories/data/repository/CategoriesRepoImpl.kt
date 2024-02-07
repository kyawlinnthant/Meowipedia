package com.everest.categories.data.repository

import CategoriesService
import com.everest.categories.data.model.categories.CategoryDTO
import com.everest.dispatcher.DispatcherModule
import com.everest.network.safeApiCall
import com.everest.util.result.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoriesRepoImpl @Inject constructor(
    private val categoriesService: CategoriesService,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) :
    CategoriesRepo {
    override suspend fun fetchCategories(): DataResult<List<CategoryDTO>> {
        return withContext(io) {
            safeApiCall {
                categoriesService.categories()
            }
        }
    }

}