package com.everest.network

import com.everest.util.result.DataResult
import com.everest.util.result.NetworkError
import kotlinx.serialization.json.Json
import retrofit2.Response
import java.net.SocketTimeoutException

inline fun <reified T> safeApiCall(
    apiCall: () -> Response<T>,
    json: Json
//    json: () -> Response<Any>
): DataResult<T> {
    return try {
        val response = apiCall()
        // 2x
        if (response.isSuccessful) {
            val body = response.body()
            DataResult.Success(data = body!!)
        } else {
            // 4x,5x
            val errorBody = response.errorBody()
            val errorResponse =
                json.decodeFromString<ServerError>(errorBody!!.string())
            // you can use localized message for server error response
            DataResult.Failed(error = NetworkError.Dynamic(message = errorResponse.message))
        }
    } catch (e: SocketTimeoutException) {
        DataResult.Failed(error = NetworkError.NoInternet)
        // you can use correct exception you want to catch
    } catch (e: Exception) {
        println("SAFE API ERROR ${e.message}")
        DataResult.Failed(error = NetworkError.SomethingWrong)
    }
}
