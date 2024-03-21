package com.everest.presentation.register

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState

@OptIn(ExperimentalFoundationApi::class)
data class RegisterUserInfoState(
    val mailTextFieldState: TextFieldState = TextFieldState(),
    val passwordTextFieldState: TextFieldState = TextFieldState(),
    val confirmPasswordTextFieldState: TextFieldState = TextFieldState(),
    val mail: String = "",
    val mailErrorMessage: String = "",
    val password: String = "",
    val passwordErrorMessage: String = "",
    val confirmPassword: String = "",
    val confirmPasswordErrorMessage: String = "",
    val isValid: Boolean = false
)
