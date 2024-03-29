package com.everest.categories.domain.usecase

import com.everest.categories.data.repository.CategoriesRepo
import com.everest.categories.domain.vo.CategoryVO
import com.everest.categories.domain.vo.toVo
import com.everest.util.result.DataResult
import javax.inject.Inject

class SearchCategories @Inject constructor(
    private val categoriesRepo: CategoriesRepo
) {

    suspend operator fun invoke(keyword: String): DataResult<List<CategoryVO>> {
        return when (val response = categoriesRepo.searchCategories(keyword = keyword)) {
            is DataResult.Failed -> DataResult.Failed(response.error)
            is DataResult.Success -> DataResult.Success(
                response.data.map {
                    it.toVo()
                }
            )
        }
    }
}
