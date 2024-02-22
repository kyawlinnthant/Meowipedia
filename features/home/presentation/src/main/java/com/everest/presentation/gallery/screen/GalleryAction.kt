package com.everest.presentation.gallery.screen

sealed interface GalleryAction {
    data object GoToCategories : GalleryAction
}
