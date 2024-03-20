package com.everest.util.validator

object PasswordValidator {

    fun isPasswordValid(password: String): String {
        if (password.isEmpty()) {
            return "Enter Password"
        }

        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$".toRegex()
        if (!passwordPattern.matches(password)) {
            return "Invalid Password Format - ( Eg - 123456Aa )"
        }
        return ""
    }

}
