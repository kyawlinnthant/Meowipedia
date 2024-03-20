package com.everest.network

import com.everest.util.result.DataResult
import com.everest.util.result.NetworkError
import java.net.SocketTimeoutException
import kotlinx.serialization.json.Json
import retrofit2.Response

inline fun <reified T> safeApiCall(
    json: Json = Json { ignoreUnknownKeys = true },
    apiCall: () -> Response<T>
): DataResult<T> {
    return try {
        val response = apiCall()

        // 2x
        if (response.isSuccessful) {
            val body = response.body()
            DataResult.Success(data = body!!)
        } else {
            // 4x,5x
            when (response.code()) {
                404 -> {
                    val errorBody = response.errorBody()
                    val errorResponse = json.decodeFromString<ServerError>(errorBody!!.string())
                    DataResult.Failed(error = NetworkError.Dynamic(message = errorResponse.message))
                }

                else -> {
                    val error = response.errorBody()?.string()
                    DataResult.Failed(error = NetworkError.Dynamic(message = error ?: "Something Wrong"))
                }
            }
        }
    } catch (e: SocketTimeoutException) {
        DataResult.Failed(error = NetworkError.NoInternet)
        // you can use correct exception you want to catch
    } catch (e: Exception) {
        println(">>> ${e.message}")
        DataResult.Failed(error = NetworkError.SomethingWrong)
    }
}
