package com.everest.network

import com.everest.util.result.DataResult
import com.everest.util.result.NetworkError
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import retrofit2.Response
import java.net.SocketTimeoutException

class SafeApiCallMockTest {
    private val json = mockk<Json>(relaxed = true)
    private val failResponse: Response<ServerError> = mockk(relaxed = true)
    private val successResponse = mockk<Response<Boolean>>(relaxed = true)

    @Test
    fun `test safeApiCall with Success Case`() {
        successResponse.apply {
            every { code() } returns 200
            every { body() } returns true
            every { isSuccessful } returns true
        }

        val result = safeApiCall {
            successResponse
        }
        assertEquals(DataResult.Success(true), result)
    }

    @Test
    @DisplayName("Every 404 Server Fail response decodes error message")
    fun `404`() = runTest {
        val errorMessage = "Not Found"
        val errorBody = "{\"message\": \"$errorMessage\"}"
            .toResponseBody("application/json".toMediaTypeOrNull())
        failResponse.apply {
            every { code() } returns 404
            every { errorBody() } returns errorBody
            every { isSuccessful } returns false
        }

        val result = safeApiCall {
            failResponse
        }
        assertEquals(
            DataResult.Failed(
                error = NetworkError.Dynamic(message = errorMessage)
            ), result
        )
    }


    @Test
    @DisplayName("Every Server Fail response decodes error message")
    fun `4xx 5xx not 404`() = runTest {
        val errorMessage = "No user"
        val errorBody = errorMessage.toResponseBody("text/plain".toMediaTypeOrNull())
        failResponse.apply {
            every { code() } returns 400
            every { errorBody() } returns errorBody
            every { isSuccessful } returns false
        }

        val result = safeApiCall {
            failResponse
        }
        assertEquals(
            DataResult.Failed(
                error = NetworkError.Dynamic(message = errorMessage)
            ), result
        )
    }


    @Test
    fun `test safeApiCall with SocketTimeoutException`() {
        coEvery {
            safeApiCall {
                failResponse
            }
        } throws SocketTimeoutException()
        val result = safeApiCall {
            failResponse
        }

        assertEquals(DataResult.Failed(NetworkError.NoInternet), result)
    }

    @Test
    fun `test safeApiCall with Exception`() {
        coEvery {
            safeApiCall {
                failResponse
            }
        } throws Exception()
        val result = safeApiCall {
            failResponse
        }

        assertEquals(DataResult.Failed(NetworkError.SomethingWrong), result)
    }

}


//inline fun <reified T : Any> mock(): T = mockk()
