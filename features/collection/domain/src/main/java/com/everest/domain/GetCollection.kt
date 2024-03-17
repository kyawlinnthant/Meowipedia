package com.everest.domain

import com.everest.data.repository.CollectionRepo
import com.everest.util.result.DataResult
import javax.inject.Inject

class GetCollection @Inject constructor(
    private val collectionRepo: CollectionRepo
) {
    suspend operator fun invoke(): DataResult<List<CollectionVO>> {
        return when (val response = collectionRepo.getCollection()) {
            is DataResult.Failed -> {
                DataResult.Failed(response.error)
            }

            is DataResult.Success -> DataResult.Success(
                response.data.map {
                    CollectionVO(
                        subId = it.subId,
                        url = it.image.url,
                        id = it.id
                    )
                }.toList()
            )
        }
    }
}
