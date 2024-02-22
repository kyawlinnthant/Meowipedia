package com.everest.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.everest.data.repository.HomeApiRepository
import com.everest.domain.model.gallery.Gallery
import com.everest.domain.model.gallery.toVo
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetGalleries @Inject constructor(
    private val apiRepo: HomeApiRepository
) {
    operator fun invoke(): Flow<PagingData<Gallery>> {
        return apiRepo.getGalleries().flow.map { pagingData ->
            pagingData.map {
                it.toVo()
            }
        }
    }
}
