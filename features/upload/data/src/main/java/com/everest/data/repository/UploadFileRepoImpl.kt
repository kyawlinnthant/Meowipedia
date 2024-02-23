package com.everest.data.repository

import com.everest.data.UploadFileDTO
import com.everest.data.service.UploadService
import com.everest.dispatcher.DispatcherModule
import com.everest.network.safeApiCall
import com.everest.util.result.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject


class UploadFileRepoImpl @Inject constructor(
    private val uploadService: UploadService,
    private val json: Json,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : UploadFileRepo {
    override suspend fun uploadFile(file: File): DataResult<UploadFileDTO> {
        val filePart = MultipartBody.Part.createFormData(
            "file",
            file.name,
            file.asRequestBody("image/*".toMediaTypeOrNull())
        )

        println(">>>> IMPL ${filePart.body}")
        return withContext(io) {
            delay(5000L)
            safeApiCall(
                apiCall = {
                    uploadService.uploadFile(
                        file = filePart,
                    )
                },
                json = json
            )
        }
    }
}
