package com.everest.data.repo

import com.everest.util.result.DataResult
import com.everest.util.result.NetworkError
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

class AuthRepoImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepo {
    override suspend fun signIn(email: String, password: String): DataResult<AuthResult> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            DataResult.Success(result)
        } catch (e: Exception) {
            DataResult.Failed(error = NetworkError.Dynamic(message = e.message ?: ""))
        }
    }

    override suspend fun register(email: String, password: String): DataResult<AuthResult> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            DataResult.Success(result)
        } catch (e: Exception) {
            DataResult.Failed(error = NetworkError.Dynamic(message = e.message ?: ""))
        }
    }
}
