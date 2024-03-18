package com.everest.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.everest.data.model.breed.BreedDTO
import com.everest.data.paging.BreedRemoteMediator
import com.everest.data.paging.MeowRemoteMediator
import com.everest.data.service.HomeApi
import com.everest.database.db.MeowDatabase
import com.everest.database.entity.breed.BreedEntity
import com.everest.database.entity.meow.MeowEntity
import com.everest.dispatcher.DispatcherModule
import com.everest.network.safeApiCall
import com.everest.util.constant.Constant
import com.everest.util.result.DataResult
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json

class HomeApiRepositoryImpl @Inject constructor(
    private val api: HomeApi,
    private val db: MeowDatabase,
    private val json: Json,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : HomeApiRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getBreeds(): Pager<Int, BreedEntity> {
        val dbSource = { db.breedDao().pagingSource(isForPaging = true) }
        val config = PagingConfig(
            initialLoadSize = PagingConfig.MAX_SIZE_UNBOUNDED,
            pageSize = Constant.PAGE_SIZE,
            maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
            jumpThreshold = 1,
            enablePlaceholders = true,
            prefetchDistance = Constant.PAGE_SIZE.div(2)
        )
        val remoteMediator = BreedRemoteMediator(
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

    override suspend fun searchBreeds(keyword: String): DataResult<List<BreedDTO>> =
        safeApiCall(
            json = json,
            apiCall = { api.searchBreeds(keyword = keyword) }
        )

    @OptIn(ExperimentalPagingApi::class)
    override fun getMeows(): Pager<Int, MeowEntity> {
        val dbSource = { db.meowDao().getPagingSource(isForPaging = true) }
        val config = PagingConfig(
            initialLoadSize = Constant.PAGE_SIZE,
            pageSize = Constant.PAGE_SIZE,
            maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
            jumpThreshold = 1,
            enablePlaceholders = true,
            prefetchDistance = Constant.PAGE_SIZE.div(2)
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
