package com.everest.presentation.screen

sealed interface GalleryAction {
    data object GoToCategories : GalleryAction
}
