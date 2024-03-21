package com.everest.data.repository

import CollectionDTO
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.everest.data.UploadFileDTO
import com.everest.data.paging.CollectionPagingSource
import com.everest.data.service.CollectionService
import com.everest.network.safeApiCall
import com.everest.util.result.DataResult
import java.io.File
import javax.inject.Inject
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

class CollectionRepoImpl @Inject constructor(
    private val collectionService: CollectionService,
    private val json: Json
) : CollectionRepo {
    override fun getCollection(): Pager<Int, CollectionDTO> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 2,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                CollectionPagingSource(collectionService = collectionService)
            }
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
