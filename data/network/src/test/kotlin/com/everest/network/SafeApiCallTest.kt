package com.everest.network

import com.everest.util.result.DataResult
import com.everest.util.result.NetworkError
import com.google.common.truth.Truth
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

class SafeApiCallTest {

    private val json = Json { ignoreUnknownKeys = true }
    private val mediaType: MediaType? = "application/json".toMediaTypeOrNull()
    private val serverErrorCode = 502
    private val successCode = 200

    private val errorMessage = "Invalid Credentials"
    private val errorResponse = json.encodeToString(
        ServerError.serializer(), ServerError(message = errorMessage)
    )
    private val errorResponseThrowable = json.encodeToString(
        ServerErrorThrowable.serializer(), ServerErrorThrowable(messages = errorMessage)
    )

    private val errorBody: ResponseBody = errorResponse.toResponseBody(mediaType)
    private val errorBodyThrowable: ResponseBody = errorResponseThrowable.toResponseBody(mediaType)
    private fun failedResponseSuccessfullyEncoded() = Response.error<ServerError>(serverErrorCode, errorBody)
    private fun failedResponseThrowsException() = Response.error<ServerErrorThrowable>(serverErrorCode, errorBodyThrowable)

    @Test
    fun `2xx success will return DataResult-Success with corresponding DTO`() {

    }

    @Test
    fun `4xx,5xx error will return DataResult-Failed with Dynamic DTO`() {
        val errorDTOResponse = DataResult.Failed(error = NetworkError.Dynamic(message = errorMessage))
        val expected = safeApiCall(json = json, apiCall = {
            failedResponseSuccessfullyEncoded()
        })
        Truth.assertThat(expected).isEqualTo(errorDTOResponse)

    }

    @Test
    fun `4xx,5xx error will return DataResult-Failed with Dynamic DTO got thrown exceptions`() {
        val errorDTOResponse =  DataResult.Failed(error = NetworkError.SomethingWrong)
        val expected = safeApiCall(json = json, apiCall = {
            failedResponseThrowsException()
        })
        Truth.assertThat(expected).isEqualTo(errorDTOResponse)
    }

}