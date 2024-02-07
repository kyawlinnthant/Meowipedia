package com.everest.categories.data.model.search

import kotlinx.serialization.Serializable


@Serializable
data class CategoryImageDTO(
    val height: Int?,
    val id: String?,
    val url: String?,
    val width: Int?
)
