package com.everest.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UploadFileDTO(
    val approved: Int,
    val height: Int,
    val id: String,
    @SerialName("original_filename")
    val originalFileName: String,
    val pending: Int,
    @SerialName("sub_id")
    val subId: String? = null,
    val url: String,
    val width: Int
)
