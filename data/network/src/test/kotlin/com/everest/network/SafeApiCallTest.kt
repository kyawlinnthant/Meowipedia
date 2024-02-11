package com.everest.network

import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.junit.Test

class SafeApiCallTest {

    private val json = Json { ignoreUnknownKeys = true }
    private val mediaType: MediaType? = "application/json".toMediaTypeOrNull()
    private val serverErrorCode = 502

    @Test
    fun `error 4xx,5xx returns DataResult Failed`(){

    }
}