package model

import kotlinx.serialization.Serializable

@Serializable
data class Weight(
    val imperial: String?,
    val metric: String?
)