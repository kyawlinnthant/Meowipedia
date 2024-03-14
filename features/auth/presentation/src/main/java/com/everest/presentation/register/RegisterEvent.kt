package com.everest.presentation.register

import com.everest.util.result.NetworkError

sealed class RegisterEvent {
    object DefaultView : RegisterEvent()
    data class ShowSnack(val error: NetworkError) : RegisterEvent()
}
