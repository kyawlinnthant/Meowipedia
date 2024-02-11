package com.everest.network

import kotlinx.serialization.Serializable

@Serializable
data class ServerErrorThrowable(
    val messages: String
)
