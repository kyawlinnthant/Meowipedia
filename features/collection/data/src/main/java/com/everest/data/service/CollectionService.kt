package com.everest.data.service

import CollectionDTO
import com.everest.data.UploadFileDTO
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface CollectionService {
    companion object {
        const val COLLECTION = "images"
        const val UPLOAD = "images/upload"
    }

    @GET(COLLECTION)
    suspend fun getCollection(
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 0,
        @Query("order") order: String = "DESC" // DESC, ASC
    ): Response<List<CollectionDTO>>

    @Multipart
    @POST(UPLOAD)
    suspend fun uploadFile(
        @Part file: MultipartBody.Part
    ): Response<UploadFileDTO>
}
