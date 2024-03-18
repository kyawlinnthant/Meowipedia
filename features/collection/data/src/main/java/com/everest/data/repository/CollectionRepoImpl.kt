package com.everest.data.repository

import CollectionDTO
import com.everest.data.UploadFileDTO
import com.everest.data.service.CollectionService
import com.everest.dispatcher.DispatcherModule
import com.everest.network.safeApiCall
import com.everest.util.result.DataResult
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class CollectionRepoImpl @Inject constructor(
    private val collectionService: CollectionService,
    private val json: Json,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : CollectionRepo {

    override suspend fun getCollection(): DataResult<List<CollectionDTO>> {
        return safeApiCall(
            apiCall = {
                collectionService.getCollection()
            },
            json = json
        )
    }

    override suspend fun uploadFile(file: File): DataResult<UploadFileDTO> {
        val requestFile = file.asRequestBody("image/jpeg".toMediaType())
        val body = MultipartBody.Part.createFormData(
            "file",
            file.name,
            requestFile
        )

        return safeApiCall(
            apiCall = {
                collectionService.uploadFile(
                    file = body
                )
            },
            json = json
        )
    }
}
