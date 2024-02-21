package com.everest.data.service

import com.everest.data.model.MeowDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryApi {

    companion object {
        private const val IMAGES = "images"
        const val GALLERY = "$IMAGES/search"
    }

    @GET(GALLERY)
    suspend fun fetchGallery(
        @Query("limit") size: Int,
        @Query("page") page: Int
    ): List<MeowDTO>
}
