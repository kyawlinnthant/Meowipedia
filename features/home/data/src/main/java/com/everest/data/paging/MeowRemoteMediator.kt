package com.everest.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.everest.data.service.HomeApi
import com.everest.database.db.MeowDatabase
import com.everest.database.entity.meow.MeowEntity
import com.everest.database.entity.meow.MeowKeyEntity
import com.everest.util.constant.Constant
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MeowRemoteMediator @Inject constructor(
    private val api: HomeApi,
    private val db: MeowDatabase
) : RemoteMediator<Int, MeowEntity>() {
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
            val response = api.meows(page = currentPage, limit = state.config.pageSize)
            val isEndOfList = response.isEmpty()
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.meowDao().deleteAllPageable(isForPaging = true)
                    db.meowKeyDao().deleteAll()
                }
                val prevKey = if (currentPage == Constant.START_PAGE) null else currentPage - 1
                val nextKey = if (isEndOfList) null else currentPage + 1
                val keys = response.map {
                    it.toKey(
                        next = nextKey,
                        prev = prevKey,
                        current = currentPage
                    )
                }
                // save BREEDS with paging status : false
                // save MEOWS with paging status : true
                // save MEOWS KEY
                val breeds = response.flatMap {
                    it.breeds.map { breed ->
                        breed.toEntity(isForPaging = false)
                    }
                }
                db.breedDao().insertBreeds(breeds)

                val meows = response.map { it.toEntity(isForPaging = true) }
                db.meowDao().insertMeows(meows)
                db.meowKeyDao().insertKeys(keys)
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
                remoteKeys?.currentPage ?: Constant.START_PAGE
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
                db.meowKeyDao().getKeyById(catId)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, MeowEntity>): MeowKeyEntity? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { cat ->
                db.meowKeyDao().getKeyById(cat.id)
            }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, MeowEntity>): MeowKeyEntity? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { cat ->
                db.meowKeyDao().getKeyById(cat.id)
            }
    }
}
