package com.everest.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.everest.data.repository.CollectionRepo
import com.everest.domain.model.CollectionVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCollection @Inject constructor(
    private val collectionRepo: CollectionRepo
) {
    operator fun invoke(): Flow<PagingData<CollectionVO>> {
        return collectionRepo.getCollection().flow.map { pagingData ->
            pagingData.map {
                CollectionVO(
                    subId = it.subId ?: "",
                    url = it.url,
                    id = it.id,
                    width = it.width,
                    height = it.height
                )
            }
        }
    }
}
