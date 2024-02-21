package com.everest.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.everest.data.paging.MeowRemoteMediator
import com.everest.data.service.GalleryApi
import com.everest.database.db.MeowDatabase
import com.everest.database.entity.MeowEntity
import com.everest.util.constant.Constant
import javax.inject.Inject

class GalleryApiRepositoryImpl @Inject constructor(
    private val api: GalleryApi,
    private val db: MeowDatabase
) : GalleryApiRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getGalleries(): Pager<Int, MeowEntity> {

        val dbSource = { db.meowDao().pagingSource() }
        val config = PagingConfig(
            initialLoadSize = Constant.INITIAL_LOAD_SIZE,
            pageSize = Constant.PAGE_SIZE,
            maxSize = Constant.MAX_LOAD_SIZE,
            jumpThreshold = 1,
            enablePlaceholders = true,
            prefetchDistance = Constant.PREFETCH_DISTANCE,
        )
        val remoteMediator = MeowRemoteMediator(
            api = api,
            db = db
        )
        return Pager(
            config = config,
            initialKey = Constant.START_PAGE,
            remoteMediator = remoteMediator,
            pagingSourceFactory = dbSource,
        )
    }
}
