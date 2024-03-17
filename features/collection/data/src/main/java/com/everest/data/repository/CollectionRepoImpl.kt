package com.everest.data.repository

import CollectionDTO
import com.everest.data.service.CollectionService
import com.everest.dispatcher.DispatcherModule
import com.everest.network.safeApiCall
import com.everest.util.result.DataResult
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class CollectionRepoImpl @Inject constructor(
    private val collectionService: CollectionService,
    private val json: Json,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : CollectionRepo {

    override suspend fun getCollection(): DataResult<List<CollectionDTO>> {
        return withContext(io) {
            delay(5000L)
            safeApiCall(
                apiCall = {
                    collectionService.getCollection()
                },
                json = json
            )
        }
    }
}
