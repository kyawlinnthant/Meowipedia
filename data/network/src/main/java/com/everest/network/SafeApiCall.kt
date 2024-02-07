package com.everest.network

import kotlinx.serialization.json.Json
import retrofit2.Response
import java.net.SocketTimeoutException

inline fun <reified T> safeApiCall(
    apiCall: () -> Response<T>,
//    json : Json // todo : if we have to catch server 4x,5x error response
): NetworkResult<T> {
    return try {
        val response = apiCall()
        // 2x
        if (response.isSuccessful) {
            val body = response.body()
            NetworkResult.Success(data = body!!)
        }
        // 4x,5x
        val errorBody = response.errorBody()
        //todo : Does server response error in errorBody?
        //  decode error response with Json
        NetworkResult.Failed(error = NetworkError.Dynamic(message = errorBody.toString()))

    } catch (e: Exception) {
        // we have to catch the exact exception for better code quality.
        if (e is SocketTimeoutException)
            NetworkResult.Failed(error = NetworkError.NoInternet)
        else
            NetworkResult.Failed(error = NetworkError.SomethingWrong)
    }
}
