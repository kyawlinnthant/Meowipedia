package com.everest.categories.data.repository

import com.everest.categories.data.model.categories.CategoryDTO
import com.everest.categories.data.service.CategoriesService
import com.everest.dispatcher.DispatcherModule
import com.everest.network.safeApiCall
import com.everest.util.result.DataResult
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class CategoriesRepoImpl @Inject constructor(
    private val categoriesService: CategoriesService,
    private val json: Json,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) :
    CategoriesRepo {
    override suspend fun fetchCategories(): DataResult<List<CategoryDTO>> {
        return withContext(io) {
            safeApiCall(json = json, apiCall = { categoriesService.categories() })
        }
    }
}
