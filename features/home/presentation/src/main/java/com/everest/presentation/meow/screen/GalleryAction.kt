package com.everest.presentation.meow.screen

sealed interface GalleryAction {
    data object GoToCategories : GalleryAction
    data object GoToUpload : GalleryAction
}
