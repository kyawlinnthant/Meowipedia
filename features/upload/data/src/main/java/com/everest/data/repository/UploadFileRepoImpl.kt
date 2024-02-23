package com.everest.data.repository

import com.everest.data.UploadFileDTO
import com.everest.data.service.UploadService
import com.everest.dispatcher.DispatcherModule
import com.everest.network.safeApiCall
import com.everest.util.result.DataResult
import java.io.File
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

class UploadFileRepoImpl @Inject constructor(
    private val uploadService: UploadService,
    private val json: Json,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : UploadFileRepo {
    override suspend fun uploadFile(file: File): DataResult<UploadFileDTO> {
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData(
            "file",
            file.name,
            requestFile
        )

        return withContext(io) {
            delay(5000L)
            safeApiCall(
                apiCall = {
                    uploadService.uploadFile(
                        file = body
                    )
                },
                json = json
            )
        }
    }
}
