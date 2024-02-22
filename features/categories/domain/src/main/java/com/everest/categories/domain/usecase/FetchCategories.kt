package com.everest.categories.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.everest.categories.domain.toVo
import com.everest.categories.data.repository.CategoriesRepo
import com.everest.categories.domain.vo.CategoryVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchCategories @Inject constructor(
    private val categoryRepo: CategoriesRepo
) {
    operator fun invoke(): Flow<PagingData<CategoryVO>> {
        return categoryRepo.getCategories().flow.map { pagingData ->
            pagingData.map {
                it.toVo()
            }
        }
    }
}
