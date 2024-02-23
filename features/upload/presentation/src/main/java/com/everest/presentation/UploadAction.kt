package com.everest.presentation

import android.net.Uri
import java.io.File

sealed interface UploadAction {
    data object Reset : UploadAction
    data class Upload(val file: File) : UploadAction
    data object Back : UploadAction

    data class FileSelect(val uri: Uri) : UploadAction
}
