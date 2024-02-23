package com.everest.data.service

import com.everest.data.model.breed.BreedDTO
import com.everest.data.model.meow.MeowDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    companion object {
        private const val IMAGES = "images"
        const val MEOWS = "$IMAGES/search"
        const val BREEDS = "breeds"
        const val SEARCH_BREEDS = "$BREEDS/search"
    }

    @GET(MEOWS)
    suspend fun meows(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("order") order: String = "RANDOM", // ASC, DESC
        @Query("size") size: String = "med", // full
        @Query("has_breeds") includeBreeds: Boolean = true
    ): List<MeowDTO>

    @GET(BREEDS)
    suspend fun breeds(
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 0
    ): List<BreedDTO>

    @GET(SEARCH_BREEDS)
    suspend fun searchBreeds(
        @Query("q") keyword: String
    ): Response<List<BreedDTO>>
}
