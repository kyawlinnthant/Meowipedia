package com.everest.network

import kotlinx.serialization.Serializable

@Serializable
data class ServerError(
    val message: String
)
