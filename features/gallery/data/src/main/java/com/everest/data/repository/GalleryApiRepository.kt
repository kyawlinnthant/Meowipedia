package com.everest.data.repository

import androidx.paging.Pager
import com.everest.database.entity.MeowEntity

interface GalleryApiRepository {
    fun getGalleries() : Pager<Int, MeowEntity>
}
