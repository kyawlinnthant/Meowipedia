package com.everest.data.repository

import com.everest.data.UploadFileDTO
import com.everest.util.result.DataResult
import java.io.File

interface UploadFileRepo {
    suspend fun uploadFile(file: File): DataResult<UploadFileDTO>
}
