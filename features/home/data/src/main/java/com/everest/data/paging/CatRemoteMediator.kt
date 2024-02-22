package com.everest.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.everest.data.service.HomeApi
import com.everest.database.db.MeowDatabase
import com.everest.database.entity.CategoryEntity
import com.everest.database.entity.CategoryKeyEntity
import javax.inject.Inject
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class CatRemoteMediator @Inject constructor(
    private val api: HomeApi,
    private val db: MeowDatabase
) : RemoteMediator<Int, CategoryEntity>() {

    override suspend fun initialize(): InitializeAction {
        return if (shouldFetchInitialPage()) InitializeAction.LAUNCH_INITIAL_REFRESH else InitializeAction.SKIP_INITIAL_REFRESH
    }

    private suspend fun shouldFetchInitialPage(): Boolean {
        return db.meowDao().getMeows().isEmpty()
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CategoryEntity>
    ): MediatorResult {
        val currentPage = getPage(loadType, state) ?: return MediatorResult.Success(
            endOfPaginationReached = false
        )

        return try {
            val response = api.categories(
                page = currentPage,
                limit = state.config.pageSize
            )
            val isEndOfList = response.isEmpty()
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.meowDao().deleteAll()
                    db.meowKeyDao().deleteAll()
                }
                val prevKey = if (currentPage == 1) null else currentPage - 1
                val nextKey = if (isEndOfList) null else currentPage + 1
                val keys = response.map {
                    it.toKeyEntity(
                        next = nextKey,
                        prev = prevKey,
                        current = currentPage
                    )
                }
                db.categoryKeyDao().addMeowKeys(keys)
                db.categoryDao().insertCategories(response.map { it.toCategoryEntity() })
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
        state: PagingState<Int, CategoryEntity>
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

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, CategoryEntity>): CategoryKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                db.categoryKeyDao().getCategoryKey(id)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, CategoryEntity>): CategoryKeyEntity? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { cat ->
                db.categoryKeyDao().getCategoryKey(cat.id)
            }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, CategoryEntity>): CategoryKeyEntity? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { cat ->
                db.categoryKeyDao().getCategoryKey(cat.id)
            }
    }
}
