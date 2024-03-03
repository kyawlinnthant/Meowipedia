package com.everest.domain.usecase

import com.everest.data.repo.SignInRepo
import com.everest.domain.model.SignInResult
import com.everest.domain.model.UserData
import com.everest.util.result.DataResult
import javax.inject.Inject

class Register @Inject constructor(private val signInRepo: SignInRepo) {
    suspend operator fun invoke(email: String, password: String): DataResult<SignInResult> {
        val result = signInRepo.register(email = email, password = password)
        return DataResult.Success(
            SignInResult(
                data = UserData(
                    "1", "Testing", "ERROR"
                ),
                errorMessage = null
            ),
        )
    }
}
