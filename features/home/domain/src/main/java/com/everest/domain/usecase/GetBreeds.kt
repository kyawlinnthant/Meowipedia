package com.everest.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.everest.data.repository.HomeApiRepository
import com.everest.domain.model.categories.breed.BreedVo
import com.everest.domain.model.categories.breed.toVo
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetBreeds @Inject constructor(
    private val apiRepo: HomeApiRepository
) {
    operator fun invoke(): Flow<PagingData<BreedVo>> {
        return apiRepo.getBreeds().flow.map { pagingData ->
            pagingData.map {
                it.toVo()
            }
        }
    }
}
