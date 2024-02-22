package com.everest.data.service

import com.everest.data.model.categories.CategoryDTO
import com.everest.data.model.gallery.MeowDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    companion object {
        private const val IMAGES = "images"
        const val GALLERY = "$IMAGES/search"
        const val BREEDS = "breeds"
        const val SEARCH_CATEGORY = "$BREEDS/search"
    }

    @GET(GALLERY)
    suspend fun fetchGallery(
        @Query("limit") size: Int,
        @Query("page") page: Int
    ): List<MeowDTO>

    @GET(BREEDS)
    suspend fun categories(
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 0
    ): List<CategoryDTO>

    @GET(SEARCH_CATEGORY)
    suspend fun searchCategories(
        @Query("q") keyword: String
    ): Response<List<CategoryDTO>>
}
