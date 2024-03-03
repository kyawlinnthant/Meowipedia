package com.everest.presentation


sealed interface SignInAction {
    data class Navigate(val route: String) : SignInAction
    data object SignIn : SignInAction
    data class Register(val mail: String, val password: String) : SignInAction
}
