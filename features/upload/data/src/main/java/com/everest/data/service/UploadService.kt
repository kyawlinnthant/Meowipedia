package com.everest.data.service

import com.everest.data.UploadFileDTO
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadService {
    companion object {
        const val UPLOAD = "images/upload"
    }

    @Multipart
    @POST(UPLOAD)
    suspend fun uploadFile(
        @Part file: MultipartBody.Part
    ): Response<UploadFileDTO>
}
