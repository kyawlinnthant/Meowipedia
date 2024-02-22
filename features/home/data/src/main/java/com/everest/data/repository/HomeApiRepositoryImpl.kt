package com.everest.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.everest.data.model.categories.CategoryDTO
import com.everest.data.paging.CatRemoteMediator
import com.everest.data.paging.MeowRemoteMediator
import com.everest.data.service.HomeApi
import com.everest.database.db.MeowDatabase
import com.everest.database.entity.CategoryEntity
import com.everest.database.entity.MeowEntity
import com.everest.dispatcher.DispatcherModule
import com.everest.network.safeApiCall
import com.everest.util.constant.Constant
import com.everest.util.result.DataResult
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class HomeApiRepositoryImpl @Inject constructor(
    private val api: HomeApi,
    private val db: MeowDatabase,
    private val json: Json,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : HomeApiRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getCategories(): Pager<Int, CategoryEntity> {
        val dbSource = { db.categoryDao().pagingSource() }
        val config = PagingConfig(
            initialLoadSize = Constant.INITIAL_LOAD_SIZE,
            pageSize = Constant.PAGE_SIZE,
            maxSize = Constant.MAX_LOAD_SIZE,
            jumpThreshold = 1,
            enablePlaceholders = true,
            prefetchDistance = Constant.PREFETCH_DISTANCE
        )
        val remoteMediator = CatRemoteMediator(
            api = api,
            db = db
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
                apiCall = { api.searchCategories(keyword = keyword) }
            )
        }

    @OptIn(ExperimentalPagingApi::class)
    override fun getGalleries(): Pager<Int, MeowEntity> {
        val dbSource = { db.meowDao().pagingSource() }
        val config = PagingConfig(
            initialLoadSize = Constant.INITIAL_LOAD_SIZE,
            pageSize = Constant.PAGE_SIZE,
            maxSize = Constant.MAX_LOAD_SIZE,
            jumpThreshold = 1,
            enablePlaceholders = true,
            prefetchDistance = Constant.PREFETCH_DISTANCE
        )
        val remoteMediator = MeowRemoteMediator(
            api = api,
            db = db
        )
        return Pager(
            config = config,
            initialKey = Constant.START_PAGE,
            remoteMediator = remoteMediator,
            pagingSourceFactory = dbSource
        )
    }
}
