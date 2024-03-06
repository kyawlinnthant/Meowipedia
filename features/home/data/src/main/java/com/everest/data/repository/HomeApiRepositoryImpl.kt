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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

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
            initialLoadSize = Constant.INITIAL_LOAD_SIZE,
            pageSize = Constant.PAGE_SIZE,
            maxSize = Constant.MAX_LOAD_SIZE,
            jumpThreshold = 1,
            enablePlaceholders = true,
            prefetchDistance = Constant.PREFETCH_DISTANCE
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
        withContext(io) {
            safeApiCall(
                json = json,
                apiCall = { api.searchBreeds(keyword = keyword) }
            )
        }

    @OptIn(ExperimentalPagingApi::class)
    override fun getMeows(): Pager<Int, MeowEntity> {
        val dbSource = { db.meowDao().pagingSource(isForPaging = true) }
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

    override fun getMeowById(id: String): Flow<DataResult<Unit>> = flow {
        withContext(io) {
            when (val response = safeApiCall { api.getMeowById(id) }) {
                is DataResult.Failed -> emit(DataResult.Failed(error = response.error))
                is DataResult.Success -> {
                    db.meowDao().insertMeow(response.data.toEntity(false))
                    response.data.breeds.map {
                        db.breedDao().insertBreed(it.toEntity())
                    }
                    emit(DataResult.Success(Unit))
                }
            }
        }
    }
}
