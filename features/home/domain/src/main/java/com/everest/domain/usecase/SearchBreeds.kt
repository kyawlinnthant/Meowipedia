package com.everest.domain.usecase

import com.everest.data.repository.HomeApiRepository
import com.everest.domain.model.categories.breed.BreedVo
import com.everest.domain.model.categories.breed.toVo
import com.everest.util.result.DataResult
import javax.inject.Inject

class SearchBreeds @Inject constructor(
    private val apiRepo: HomeApiRepository
) {

    suspend operator fun invoke(keyword: String): DataResult<List<BreedVo>> {
        return when (val response = apiRepo.searchBreeds(keyword = keyword)) {
            is DataResult.Failed -> DataResult.Failed(response.error)
            is DataResult.Success -> DataResult.Success(
                response.data.map {
                    it.toVo()
                }
            )
        }
    }
}
