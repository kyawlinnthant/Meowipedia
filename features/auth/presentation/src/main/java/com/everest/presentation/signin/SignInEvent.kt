package com.everest.presentation.signin

import com.everest.util.result.NetworkError

sealed class SignInEvent {
    object DefaultView : SignInEvent()
    data class ShowSnack(val error: NetworkError) : SignInEvent()
}
