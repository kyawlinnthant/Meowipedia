package com.everest.presentation

sealed interface CollectionAction {
    data class Navigate(val route: String) : CollectionAction
    data class ShowOwnCollection(val isShow: Boolean) : CollectionAction
    data object Back : CollectionAction
}
