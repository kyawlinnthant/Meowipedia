package com.everest.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.everest.data.service.HomeApi
import com.everest.database.db.MeowDatabase
import com.everest.database.entity.MeowEntity
import com.everest.database.entity.MeowKeyEntity
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class MeowRemoteMediator @Inject constructor(
    private val api: HomeApi,
    private val db: MeowDatabase
) : RemoteMediator<Int, MeowEntity>() {

    private val startPage = 1
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MeowEntity>
    ): MediatorResult {
        val currentPage = getPage(loadType, state) ?: return MediatorResult.Success(
            endOfPaginationReached = false
        )

        return try {
//            delay(1000L)
            val response = api.fetchGallery(page = currentPage, size = state.config.pageSize)
            val isEndOfList = response.isEmpty()
            withContext(Dispatchers.IO) {
                db.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        db.meowDao().deleteAll()
                        db.meowKeyDao().deleteAll()
                    }
                    val prevKey = if (currentPage == startPage) null else currentPage - 1
                    val nextKey = if (isEndOfList) null else currentPage + 1
                    val keys = response.map {
                        it.toKeyEntity(
                            next = nextKey,
                            prev = prevKey,
                            current = currentPage
                        )
                    }
                    db.meowKeyDao().addMeowKeys(keys)
                    db.meowDao().insertMeows(response.map { it.toMeowEntity() })
                }
            }
            MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getPage(
        loadType: LoadType,
        state: PagingState<Int, MeowEntity>
    ): Int? {
        return when (loadType) {
            // loading
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.currentPage ?: startPage
            }

            // has data, load more
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                remoteKeys?.nextPage
            }

            // has data, load previous
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                remoteKeys?.prevPage
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MeowEntity>): MeowKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { catId ->
                db.meowKeyDao().getMeowKey(catId)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, MeowEntity>): MeowKeyEntity? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { cat ->
                db.meowKeyDao().getMeowKey(cat.id)
            }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, MeowEntity>): MeowKeyEntity? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { cat ->
                db.meowKeyDao().getMeowKey(cat.id)
            }
    }
}
