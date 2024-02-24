package com.everest.presentation.meow.screen

sealed interface GalleryAction {
    data class Navigate(val route: String) : GalleryAction
    data object Upload : GalleryAction
}
