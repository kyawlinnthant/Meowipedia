package com.everest.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.everest.data.repository.HomeApiRepository
import com.everest.domain.model.meow.MeowVo
import com.everest.domain.model.meow.toVo
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMeows @Inject constructor(
    private val apiRepo: HomeApiRepository
) {
    operator fun invoke(): Flow<PagingData<MeowVo>> {
        return apiRepo.getMeows().flow.map { pagingData ->
            pagingData.map {
                it.toVo()
            }
        }
    }
}
