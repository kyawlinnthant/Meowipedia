package com.everest.presentation


sealed interface CollectionAction {
    data class Navigate(val route: String) : CollectionAction
    data object Back : CollectionAction
}
