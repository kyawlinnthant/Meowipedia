package com.everest.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.everest.data.repository.HomeApiRepository
import com.everest.domain.model.categories.CategoryVO
import com.everest.domain.model.categories.toVo
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchCategories @Inject constructor(
    private val apiRepo: HomeApiRepository
) {
    operator fun invoke(): Flow<PagingData<CategoryVO>> {
        return apiRepo.getCategories().flow.map { pagingData ->
            pagingData.map {
                it.toVo()
            }
        }
    }
}
