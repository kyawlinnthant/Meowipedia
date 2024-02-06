package model.search

import kotlinx.serialization.Serializable


@Serializable
data class CategoryImage(
    val height: Int?,
    val id: String?,
    val url: String?,
    val width: Int?
)
