package com.klt.paging.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.everest.categories.data.model.categories.vo.CategoryVO
import com.everest.categories.data.model.categories.vo.toVo
import com.everest.categories.data.service.CategoriesService
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CatNetworkPagingSource @Inject constructor(
    private val categoriesService: CategoriesService
) : PagingSource<Int, CategoryVO>() {

    override fun getRefreshKey(state: PagingState<Int, CategoryVO>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CategoryVO> {

        val currentPage = params.key ?: 1

        return try {
            val response = categoriesService.categories(
                page = currentPage,
                limit = params.loadSize
            )
            LoadResult.Page(
                data = response.map { it.toVo() },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.isEmpty()) null else currentPage + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}
