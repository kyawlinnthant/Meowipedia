package com.everest.presentation

import java.io.File

sealed interface CollectionAction {
    data class Navigate(val route: String) : CollectionAction
    data class ShowOwnCollection(val isShow: Boolean) : CollectionAction
    data object Back : CollectionAction
    data object DismissDialog : CollectionAction

    data class Upload(val file: File) : CollectionAction
}
