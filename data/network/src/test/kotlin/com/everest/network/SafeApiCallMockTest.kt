package com.everest.network

import com.everest.util.result.DataResult
import com.everest.util.result.NetworkError
import io.mockk.coEvery
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
    private val mockResponse: Response<ServerError> = mockk()

    @Test
    @DisplayName("Every Throwable returns error message")
    fun `throw exceptions`() = runTest {
        val errorMessage = "No user"
        val errorBody = errorMessage.toResponseBody("text/plain".toMediaTypeOrNull())


////        fun serverErrorCall() = Response.error<String>(
////            400,
////            errorBody
////        )
//        every {
//            safeApiCall(json = json) {
//                serverErrorCall()
//                mockResponse
//            }
//        }
//
//
//        val output = safeApiCall {
//            serverErrorCall()
//        }
//
//        val outputError = (output as DataResult.Failed).error
//        val outputMessage = (outputError as NetworkError.Dynamic).message
//        assertThat(outputError).isInstanceOf(NetworkError::class)
//        assertThat(outputMessage).isEqualTo(errorMessage)
    }

    @Test
    fun `test safeApiCall with SocketTimeoutException`() {
        val mockResponse: Response<String> = mockk(relaxed = true)
        coEvery {
            safeApiCall {
                mockResponse
            }
        } throws SocketTimeoutException()
        val result = safeApiCall {
            mockResponse
        }

        println(">>>> $result")
        assertEquals(DataResult.Failed(NetworkError.NoInternet), result)
    }

    @Test
    fun `test safeApiCall with Exception`() {
        val mockResponse: Response<String> = mockk(relaxed = true)
        coEvery {
            safeApiCall {
                mockResponse
            }
        } throws Exception()
        val result = safeApiCall {
            mockResponse
        }

        println(">>>> $result")
        assertEquals(DataResult.Failed(NetworkError.SomethingWrong), result)
    }

}


//inline fun <reified T : Any> mock(): T = mockk()
