package com.everest.util.result

import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed interface NetworkError {
    data class Dynamic(val message: String) : NetworkError
    data object NoInternet : NetworkError
    data object SomethingWrong : NetworkError
}

fun Throwable.toErrorType(): NetworkError {
    return when (this) {
        is SocketTimeoutException -> NetworkError.NoInternet
        is UnknownHostException -> NetworkError.NoInternet
        else -> NetworkError.SomethingWrong
    }
}
