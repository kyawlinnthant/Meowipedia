package com.everest.data.categories.service

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import com.everest.data.service.HomeApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import retrofit2.Retrofit

class HomeApiTest {
    private lateinit var service: HomeApi
    private lateinit var mockWebServer: MockWebServer

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    private val factory = json.asConverterFactory("application/json".toMediaType())

    @BeforeEach
    fun setup() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(factory)
            .build().create(HomeApi::class.java)
    }

    @AfterEach
    fun teardown() {
        mockWebServer.shutdown()
    }

    private fun enqueueResponse(fileName: String, responseCode: Int = 200) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(responseCode)
        val source = inputStream.source().buffer()
        mockWebServer.enqueue(
            mockResponse.setBody(source.readString(Charsets.UTF_8))
        )
    }

    @Test
    fun `fetch categories with limit 2 success 2xx`() = runTest {
        val limit = 2
        enqueueResponse("category_success_2_data_response.json")
        val response = service.breeds(limit = limit)
        val request = mockWebServer.takeRequest()
        // is correct request
        assertThat(request.method).isEqualTo("GET")
        assertThat(request.path)
            .isEqualTo("/" + HomeApi.BREEDS + "?limit=$limit&page=0")
        // is correct response
        assertThat(response.size).isEqualTo(limit)
    }

    @Test
    fun `fetch categories with limit 10 success 2xx`() = runTest {
        val limit = 10
        enqueueResponse("category_success_10_data_response.json")
        val response = service.breeds(limit = limit)
        val request = mockWebServer.takeRequest()
        // is correct request
        assertThat(request.method).isEqualTo("GET")
        assertThat(request.path)
            .isEqualTo("/" + HomeApi.BREEDS + "?limit=$limit&page=0")
        // is correct response
        assertThat(response.size).isEqualTo(limit)
    }

    @Test
    fun `malformed json throws exception`() = runTest {
        enqueueResponse("malformed.json")
        assertThrows<Exception> {
            service.breeds()
        }
    }

    @Test
    fun `fetch search categories with success 2xx`() = runTest {
        val keyword = "air"
        enqueueResponse("search_success_data_response.json")
        val response = service.searchBreeds(keyword = keyword)
        val request = mockWebServer.takeRequest()
        // is correct request
        assertThat(request.method).isEqualTo("GET")
        assertThat(request.path)
            .isEqualTo("/" + HomeApi.SEARCH_BREEDS + "?q=$keyword")
        // is correct response
        assertThat(response.isSuccessful).isEqualTo(true)
        response.body()?.let {
            val item = it.first()
            assertThat(item.name).contains(keyword)
        }
    }

    @Test
    fun `fetch gallery with success 2xx`() = runTest {
        val limit = 10
        enqueueResponse("gallery_success_data_response.json")
        val response = service.meows(limit = limit, page = 0)
        val request = mockWebServer.takeRequest()
        // is correct request
        assertThat(request.method).isEqualTo("GET")
        assertThat(request.path)
            .isEqualTo("/" + HomeApi.MEOWS + "?limit=$limit&page=0&order=RANDOM&size=med&has_breeds=true")
        // is correct response
        assertThat(response.size).isEqualTo(limit)
    }

    @Test
    fun `fetch search with success 400`() = runTest {
        val keyword = "testing"
        enqueueResponse("search_success_data_response.json", 400)
        val response = service.searchBreeds(keyword = keyword)
        val request = mockWebServer.takeRequest()
        // is correct request
        assertThat(request.method).isEqualTo("GET")
        assertThat(request.path)
            .isEqualTo("/" + HomeApi.SEARCH_BREEDS + "?q=$keyword")
        // is correct response
        assertThat(response.isSuccessful).isEqualTo(false)
        assertThat(response.code()).isEqualTo(400)
    }
}
