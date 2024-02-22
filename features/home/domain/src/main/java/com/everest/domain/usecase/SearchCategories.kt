package com.everest.domain.usecase

import com.everest.data.repository.HomeApiRepository
import com.everest.domain.model.categories.CategoryVO
import com.everest.domain.model.categories.toVo
import com.everest.util.result.DataResult
import javax.inject.Inject

class SearchCategories @Inject constructor(
    private val apiRepo: HomeApiRepository
) {

    suspend operator fun invoke(keyword: String): DataResult<List<CategoryVO>> {
        return when (val response = apiRepo.searchCategories(keyword = keyword)) {
            is DataResult.Failed -> DataResult.Failed(response.error)
            is DataResult.Success -> DataResult.Success(
                response.data.map {
                    it.toVo()
                }
            )
        }
    }
}
