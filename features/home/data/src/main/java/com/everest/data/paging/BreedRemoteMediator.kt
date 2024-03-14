package com.everest.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.everest.data.service.HomeApi
import com.everest.database.db.MeowDatabase
import com.everest.database.entity.breed.BreedEntity
import com.everest.database.entity.breed.BreedKeyEntity
import javax.inject.Inject
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class BreedRemoteMediator @Inject constructor(
    private val api: HomeApi,
    private val db: MeowDatabase
) : RemoteMediator<Int, BreedEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

//    private suspend fun shouldFetchInitialPage(): Boolean {
//        return db.breedDao().getBreeds().isEmpty()
//    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BreedEntity>
    ): MediatorResult {
        val currentPage = getPage(loadType, state) ?: return MediatorResult.Success(
            endOfPaginationReached = false
        )

        return try {
            val response = api.breeds(
                page = currentPage,
                limit = state.config.pageSize
            )
            val isEndOfList = response.isEmpty()
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.breedDao().deleteAllPageable(isForPaging = true)
                    db.breedKeyDao().deleteAll()
                }
                val prevKey = if (currentPage == 1) null else currentPage - 1
                val nextKey = if (isEndOfList) null else currentPage + 1
                val keys = response.map {
                    it.toKey(
                        next = nextKey,
                        prev = prevKey,
                        current = currentPage
                    )
                }
                db.breedKeyDao().insertKeys(keys)
                db.breedDao().insertBreeds(response.map { it.toEntity(isForPaging = true) })
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
        state: PagingState<Int, BreedEntity>
    ): Int? {
        return when (loadType) {
            // loading
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.currentPage ?: 1
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

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, BreedEntity>): BreedKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                db.breedKeyDao().getKeyById(id)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, BreedEntity>): BreedKeyEntity? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { cat ->
                db.breedKeyDao().getKeyById(cat.id)
            }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, BreedEntity>): BreedKeyEntity? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { cat ->
                db.breedKeyDao().getKeyById(cat.id)
            }
    }
}
