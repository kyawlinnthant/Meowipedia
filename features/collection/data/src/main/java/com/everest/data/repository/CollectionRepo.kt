package com.everest.data.repository

import CollectionDTO
import androidx.paging.Pager
import com.everest.data.UploadFileDTO
import com.everest.util.result.DataResult
import java.io.File

interface CollectionRepo {
    fun getCollection(): Pager<Int, CollectionDTO>

    suspend fun uploadFile(file: File): DataResult<UploadFileDTO>
}
