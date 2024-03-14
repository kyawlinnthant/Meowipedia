package com.everest.presentation.register

sealed interface RegisterAction {
    data object Register : RegisterAction
}
