package com.everest.data.repo

import com.everest.util.result.DataResult
import com.google.firebase.auth.AuthResult

interface AuthRepo {
    suspend fun signIn(email: String, password: String): DataResult<AuthResult>

    suspend fun register(email: String, password: String): DataResult<AuthResult>
}
