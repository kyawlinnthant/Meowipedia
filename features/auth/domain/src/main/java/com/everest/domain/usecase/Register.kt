package com.everest.domain.usecase

import com.everest.data.repo.AuthRepo
import com.everest.domain.model.SignInResult
import com.everest.domain.model.UserData
import com.everest.util.result.DataResult
import com.everest.util.result.NetworkError
import javax.inject.Inject

class Register @Inject constructor(private val signInRepo: AuthRepo) {
    suspend operator fun invoke(email: String, password: String): DataResult<SignInResult> {
        val result = signInRepo.register(email = email, password = password)
        return when (result) {
            is DataResult.Failed -> {
                DataResult.Failed(error = result.error)
            }

            is DataResult.Success -> {
                result.data.user?.let {
                    DataResult.Success(
                        SignInResult(
                            data = UserData(
                                it.uid,
                                it.displayName,
                                ""
                            ),
                            errorMessage = null
                        )
                    )
                } ?: run {
                    DataResult.Failed(error = NetworkError.Dynamic(message = "No User"))
                }
            }
        }
    }
}
