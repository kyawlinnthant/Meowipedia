package com.everest.domain.usecase

import com.everest.data.repo.AuthRepo
import com.everest.domain.model.SignInResult
import com.everest.domain.model.UserData
import com.everest.util.result.DataResult
import com.everest.util.result.NetworkError
import javax.inject.Inject

class SignIn @Inject constructor(private val signInRepo: AuthRepo) {
    suspend operator fun invoke(email: String, password: String): DataResult<SignInResult> {
        return when (val result = signInRepo.signIn(email = email, password = password)) {
            is DataResult.Failed -> DataResult.Failed(result.error)

            is DataResult.Success -> {
                val data = result.data
                data.user?.let {
                    DataResult.Success(
                        SignInResult(
                            data = UserData(
                                userId = it.uid,
                                username = it.displayName,
                                profilePictureUrl = it.photoUrl.toString()
                            ),
                            errorMessage = null
                        )
                    )
                } ?: run {
                    DataResult.Failed(NetworkError.SomethingWrong)
                }
            }
        }
    }
}
