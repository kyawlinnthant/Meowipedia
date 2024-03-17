package com.everest.data.service

import CollectionDTO
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST

interface CollectionService {
    companion object {
        const val COLLECTION = "favourites"
    }

    @Multipart
    @POST(COLLECTION)
    suspend fun getCollection(): Response<List<CollectionDTO>>
}
