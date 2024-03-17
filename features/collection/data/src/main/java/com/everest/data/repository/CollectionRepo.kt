package com.everest.data.repository

import CollectionDTO
import com.everest.util.result.DataResult

interface CollectionRepo {
    suspend fun getCollection(): DataResult<List<CollectionDTO>>
}
