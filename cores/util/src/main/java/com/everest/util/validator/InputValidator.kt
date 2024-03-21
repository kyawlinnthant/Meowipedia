package com.everest.util.validator

import com.everest.extensions.isEmailValid

object InputValidator {
    fun emailValidator(value: String): String {
        return when {
            value.isEmpty() -> "Enter Value"
            !value.isEmailValid() -> "Enter Valid Email"
            else -> ""
        }
    }
}
