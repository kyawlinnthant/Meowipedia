package com.everest.presentation.meow.screen

sealed interface MeowsAction {
    data class Navigate(val route: String) : MeowsAction
    data object Upload : MeowsAction
}
