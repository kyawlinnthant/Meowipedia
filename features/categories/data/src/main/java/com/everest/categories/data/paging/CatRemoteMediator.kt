package com.everest.categories.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.everest.categories.data.model.categories.toCategoriesRemoteKey
import com.everest.categories.data.model.categories.toEntity
import com.everest.categories.data.service.CategoriesService
import com.everest.database.db.MeowDatabase
import com.everest.database.entity.MeowEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CatRemoteMediator @Inject constructor(
    private val categoriesService: CategoriesService,
    private val meowDatabase: MeowDatabase
) : RemoteMediator<Int, MeowEntity>() {

    override suspend fun initialize(): InitializeAction {
        return if (shouldFetchInitialPage()) InitializeAction.LAUNCH_INITIAL_REFRESH else InitializeAction.SKIP_INITIAL_REFRESH
    }

    private fun shouldFetchInitialPage(): Boolean {
        return meowDatabase.provideMeowDao().getMeowList().isEmpty()
    }

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, MeowEntity>
    ): MediatorResult {

        val currentPage = getPage(loadType, state) ?: return MediatorResult.Success(
            endOfPaginationReached = false
        )

        return try {
            delay(1000L)
            val response = categoriesService.categories(
                page = currentPage,
                limit = state.config.pageSize
            )
            val isEndOfList = response.isEmpty()
            withContext(Dispatchers.IO) {
                meowDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        meowDatabase.provideMeowDao().deleteAll()
                        meowDatabase.provideCategoriesRemoteKeyDao().deleteAll()
                    }
                    val prevKey = if (currentPage == 1) null else currentPage - 1
                    val nextKey = if (isEndOfList) null else currentPage + 1
                    val keys = response.map {
                        it.toCategoriesRemoteKey(
                            nextPage = nextKey,
                            prevPage = prevKey,
                            currentPage = currentPage
                        )
                    }
                    meowDatabase.provideCategoriesRemoteKeyDao().addRemoteKeys(keys)
                    meowDatabase.provideMeowDao().insertMeow(response.map { it.toEntity() })
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

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MeowEntity>): RemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { catId ->
                meowDatabase.provideCategoriesRemoteKeyDao().getRemoteKey(catId)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, MeowEntity>): RemoteKeyEntity? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { cat ->
                meowDatabase.provideCategoriesRemoteKeyDao().getRemoteKey(cat.id)
            }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, MeowEntity>): RemoteKeyEntity? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { cat ->
                meowDatabase.provideCategoriesRemoteKeyDao().getRemoteKey(cat.id)
            }

    }
}
