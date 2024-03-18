package com.everest.data.service

import CollectionDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST

interface CollectionService {
    companion object {
        const val COLLECTION = "favourites"
    }

    @GET(COLLECTION)
    suspend fun getCollection(): Response<List<CollectionDTO>>
}
