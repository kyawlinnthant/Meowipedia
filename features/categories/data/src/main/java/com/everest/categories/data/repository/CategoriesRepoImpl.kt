package com.everest.categories.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.everest.categories.data.model.categories.CategoryDTO
import com.everest.categories.data.paging.CatRemoteMediator
import com.everest.categories.data.service.CategoriesService
import com.everest.database.db.MeowDatabase
import com.everest.database.entity.CategoryEntity
import com.everest.dispatcher.DispatcherModule
import com.everest.network.safeApiCall
import com.everest.util.constant.Constant
import com.everest.util.result.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CategoriesRepoImpl @Inject constructor(
    private val categoriesService: CategoriesService,
    private val meowDatabase: MeowDatabase,
    private val json: Json,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) :
    CategoriesRepo {
    @OptIn(ExperimentalPagingApi::class)
    override fun getCategories(): Pager<Int, CategoryEntity> {
        val dbSource = { meowDatabase.categoryDao().pagingSource() }
        val config = PagingConfig(
            initialLoadSize = Constant.INITIAL_LOAD_SIZE,
            pageSize = Constant.PAGE_SIZE,
            maxSize = Constant.MAX_LOAD_SIZE,
            jumpThreshold = 1,
            enablePlaceholders = true,
            prefetchDistance = Constant.PREFETCH_DISTANCE
        )
        val remoteMediator = CatRemoteMediator(
           categoriesService = categoriesService,
            meowDatabase = meowDatabase,
        )
        return Pager(
            config = config,
            initialKey = Constant.START_PAGE,
            remoteMediator = remoteMediator,
            pagingSourceFactory = dbSource
        )
    }


    override suspend fun searchCategories(keyword: String): DataResult<List<CategoryDTO>> =
        withContext(io) {
            safeApiCall(
                json = json,
                apiCall = { categoriesService.searchCategories(keyword = keyword) }
            )
        }
}
