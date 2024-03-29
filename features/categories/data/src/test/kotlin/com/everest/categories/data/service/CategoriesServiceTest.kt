package com.everest.categories.data.service

import com.google.common.truth.Truth
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import java.net.HttpURLConnection

class CategoriesServiceTest {
    private lateinit var service: CategoriesService
    private lateinit var mockWebServer: MockWebServer

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
    private val factory = json.asConverterFactory("application/json".toMediaType())

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(factory)
            .build().create(CategoriesService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    private fun enqueueResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val mockResponse = MockResponse()
        val source = inputStream.source().buffer()
        mockWebServer.enqueue(
            mockResponse.setBody(source.readString(Charsets.UTF_8)),
        )
    }

    @Test
    fun `fetch categories with limit 2 success 2xx`() = runTest {
        val limit = 2
        enqueueResponse("success_2_data_response.json")
        val response = service.categories(limit = limit)
        val request = mockWebServer.takeRequest()
        // is correct request
        Truth.assertThat(request.method).isEqualTo("GET")
        Truth.assertThat(request.path)
            .isEqualTo("/" + CategoriesService.CATEGORY + "?limit=$limit&page=0")
        // is correct response
        val responseBody = response.body()!!
        Truth.assertThat(response.code()).isEqualTo(HttpURLConnection.HTTP_OK)
        Truth.assertThat(responseBody.size).isEqualTo(limit)
    }

    @Test
    fun `fetch categories with limit 20 success 2xx`() = runTest {
        val limit = 20
        enqueueResponse("success_20_data_response.json")
        val response = service.categories(limit = limit)
        val request = mockWebServer.takeRequest()
        // is correct request
        Truth.assertThat(request.method).isEqualTo("GET")
        Truth.assertThat(request.path)
            .isEqualTo("/" + CategoriesService.CATEGORY + "?limit=$limit&page=0")
        // is correct response
        val responseBody = response.body()!!
        Truth.assertThat(response.code()).isEqualTo(HttpURLConnection.HTTP_OK)
        Truth.assertThat(responseBody.size).isEqualTo(limit)
    }

    @Test(expected = Exception::class)
    fun `malformed json throws exception`() = runTest {
        enqueueResponse("malformed.json")
        service.categories()
    }

}