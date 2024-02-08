package com.everest.network

import com.everest.util.result.DataResult
import com.everest.util.result.NetworkError
import retrofit2.Response
import java.net.SocketTimeoutException

inline fun <reified T> safeApiCall(
    apiCall: () -> Response<T>,
//    json : Json // todo : if we have to catch server 4x,5x error response
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
            //todo : Does server response error in errorBody?
            //  decode error response with Json
            DataResult.Failed(error = NetworkError.Dynamic(message = errorBody.toString()))
        }


    } catch (e: Exception) {
        println(">>>> ${e.message}")
        // we have to catch the exact exception for better code quality.
        if (e is SocketTimeoutException)
            DataResult.Failed(error = NetworkError.NoInternet)
        else
            DataResult.Failed(error = NetworkError.SomethingWrong)
    }
}
