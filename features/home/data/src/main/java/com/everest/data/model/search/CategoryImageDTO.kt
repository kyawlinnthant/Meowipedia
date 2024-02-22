package com.everest.data.model.search

import kotlinx.serialization.Serializable

@Serializable
data class CategoryImageDTO(
    val height: Int? = -1,
    val id: String? = "",
    val url: String? = "",
    val width: Int? = -1
)
