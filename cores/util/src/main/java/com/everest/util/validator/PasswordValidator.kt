package com.everest.util.validator

object PasswordValidator {

    fun isPasswordValid(password: String): String {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$".toRegex()

        return when {
            password.isEmpty() -> "Enter Password"
            !passwordPattern.matches(password) -> "Invalid Password Format - ( Eg - 123456Aa )"
            else -> ""
        }
    }

    fun isPasswordMatch(password: String, confirmPassword: String): String {
        if (password.isNotEmpty() &&
            confirmPassword.isNotEmpty() &&
            password != confirmPassword
        ) {
            return "Password don't match"
        }
        return ""
    }
}
