package com.everest.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.everest.data.repository.GalleryApiRepository
import com.everest.domain.model.Gallery
import com.everest.domain.model.toVo
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetGalleries @Inject constructor(
    private val repo: GalleryApiRepository
) {
    operator fun invoke(): Flow<PagingData<Gallery>> {
        return repo.getGalleries().flow.map { pagingData ->
            pagingData.map {
                it.toVo()
            }
        }
    }
}
