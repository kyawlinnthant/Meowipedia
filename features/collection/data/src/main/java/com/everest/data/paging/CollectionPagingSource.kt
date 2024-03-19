package com.everest.data.paging

import CollectionDTO
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.everest.data.service.CollectionService

class CollectionPagingSource(
    private val collectionService: CollectionService
) : PagingSource<Int, CollectionDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CollectionDTO> {
        var pageNumber = params.key ?: 0
        return try {
            val response = collectionService.getCollection(page = pageNumber)
            val pageResponse = response.body()
            if (response.isSuccessful) {
                pageNumber++
            }

            LoadResult.Page(
                data = pageResponse.orEmpty(),
                prevKey = null,
                nextKey = pageNumber
            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, CollectionDTO>): Int =
        ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(0)

}
