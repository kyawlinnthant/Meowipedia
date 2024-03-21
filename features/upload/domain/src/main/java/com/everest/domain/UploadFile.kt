package com.everest.domain

import com.everest.data.repository.UploadFileRepo
import com.everest.util.result.DataResult
import java.io.File
import javax.inject.Inject

class UploadFile @Inject constructor(
    private val uploadFileRepo: UploadFileRepo
) {
    suspend operator fun invoke(file: File): DataResult<Boolean> {
        val response = uploadFileRepo.uploadFile(file = file)
        return when (response) {
            is DataResult.Failed -> {
                DataResult.Failed(response.error)
            }

            is DataResult.Success -> DataResult.Success(true)
        }
    }
}
