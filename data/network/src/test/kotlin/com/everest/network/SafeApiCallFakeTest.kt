package com.everest.network

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.everest.util.result.DataResult
import com.everest.util.result.NetworkError
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import retrofit2.Response

class SafeApiCallFakeTest {

    private lateinit var json: Json

    @BeforeEach
    fun setup() {
        json = Json { ignoreUnknownKeys = true }
    }

    @Test
    @DisplayName("Every Successful API call decodes expected DTO")
    fun `2xx`() = runTest {
        fun successCall() = Response.success(true)
        val expected = DataResult.Success(data = true)
        val output = safeApiCall { successCall() }
        assertThat(output).isEqualTo(expected)
    }

    @Test
    @DisplayName("Every 404 Server Fail response decodes error message")
    fun `404`() = runTest {
        val errorMessage = "Not Found"
        val errorDto = ServerError(
            message = errorMessage
        )
        val errorJson = json.encodeToString(ServerError.serializer(), errorDto)
        val errorBody = errorJson.toResponseBody("application/json".toMediaTypeOrNull())
        fun serverErrorCall() = Response.error<ServerError>(
            404,
            errorBody
        )

        val expected = DataResult.Failed(
            error = NetworkError.Dynamic(message = errorMessage)
        )
        val output = safeApiCall {
            serverErrorCall()
        }
        assertThat(output).isEqualTo(expected)
    }


    @Test
    @DisplayName("Every Server Fail response decodes error message")
    fun `4xx 5xx not 404`() = runTest {
        val errorMessage = "No user"
        val errorBody = errorMessage.toResponseBody("text/plain".toMediaTypeOrNull())

        fun serverErrorCall() = Response.error<String>(
            400,
            errorBody
        )

        val output = safeApiCall {
            serverErrorCall()
        }

        val outputError = (output as DataResult.Failed).error
        val outputMessage = (outputError as NetworkError.Dynamic).message
        assertThat(outputError).isInstanceOf(NetworkError::class)
        assertThat(outputMessage).isEqualTo(errorMessage)
    }

    @Test
    @DisplayName("Every Throwable returns error message")
    fun `throw exceptions`() = runTest {

    }

}
