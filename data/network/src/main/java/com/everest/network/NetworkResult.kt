package com.everest.network

sealed class NetworkResult<out T>(
    open val data: T? = null,
) {
    data class Success<out T>(override val data: T) : NetworkResult<T>()
    data class Failed(val error: NetworkError) : NetworkResult<Nothing>()
}


sealed interface NetworkError {
    data class Dynamic(val message: String) : NetworkError
    data object NoInternet : NetworkError
    data object SomethingWrong : NetworkError
}