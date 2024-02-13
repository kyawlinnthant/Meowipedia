package com.everest.util.result
sealed interface NetworkError {
    data class Dynamic(val message: String) : NetworkError
    data object NoInternet : NetworkError
    data object SomethingWrong : NetworkError
}
