package com.everest.data.repository

import CollectionDTO
import com.everest.data.UploadFileDTO
import com.everest.util.result.DataResult
import java.io.File

interface CollectionRepo {
    suspend fun getCollection(): DataResult<List<CollectionDTO>>

    suspend fun uploadFile(file: File): DataResult<UploadFileDTO>
}
