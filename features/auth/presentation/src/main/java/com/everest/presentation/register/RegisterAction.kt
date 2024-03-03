package com.everest.presentation.register


sealed interface RegisterAction {
    data class Register(val mail: String, val password: String) : RegisterAction
}
