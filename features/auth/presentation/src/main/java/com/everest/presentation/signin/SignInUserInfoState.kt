package com.everest.presentation.signin

data class SignInUserInfoState(
    val mail: String = "",
    val mailErrorMessage: String = "",
    val password: String = "",
    val passwordErrorMessage: String = "",
    val isValid: Boolean = false
)
