package com.everest.presentation.detail.state

sealed interface DetailAction {
    data object OnBack : DetailAction
    data class OnFavourite(val enabled : Boolean) : DetailAction
}
