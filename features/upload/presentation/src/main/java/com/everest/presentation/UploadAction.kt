package com.everest.presentation

import java.io.File

sealed interface UploadAction {
    data object Reset : UploadAction
    data class Upload(val file: File) : UploadAction
    data object Back : UploadAction

    data class FileSelect(val file: File) : UploadAction
}
