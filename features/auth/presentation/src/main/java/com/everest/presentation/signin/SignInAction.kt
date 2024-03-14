package com.everest.presentation.signin

sealed interface SignInAction {
    data class Navigate(val route: String) : SignInAction
    data object SignIn : SignInAction
}
