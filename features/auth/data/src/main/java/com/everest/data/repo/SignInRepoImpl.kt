package com.everest.data.repo

import com.everest.dispatcher.DispatcherModule
import com.everest.util.result.DataResult
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignInRepoImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : SignInRepo {
    override suspend fun signIn(email: String, password: String): DataResult<AuthResult> {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return DataResult.Success(result)
    }

    override suspend fun register(email: String, password: String): DataResult<AuthResult> {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        return DataResult.Success(result)
    }
}
