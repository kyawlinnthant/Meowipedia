package com.everest.domain.usecase

import com.everest.data.repository.HomeApiRepository
import com.everest.data.repository.HomeDbRepositoryImpl
import com.everest.domain.model.meow.MeowWithBreedsVo
import com.everest.domain.model.meow.toVo
import com.everest.util.result.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMeowById @Inject constructor(
    private val apiRepo: HomeApiRepository,
    private val dbRepo: HomeDbRepositoryImpl
) {
    suspend operator fun invoke(id: String): Flow<DataResult<MeowWithBreedsVo>> = flow {
        apiRepo.getMeowById(id).map {
            if (it is DataResult.Failed) {
                emit(it)
            }
        }
        dbRepo.getMeowById(id).map { emit(DataResult.Success(it.toVo())) }
    }
}
