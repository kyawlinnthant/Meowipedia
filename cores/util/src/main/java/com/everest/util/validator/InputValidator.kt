package com.everest.util.validator

import com.everest.extensions.isEmailValid


object InputValidator {
    fun emailValidator(value: String): String {

        if (value.isEmpty())
            return "Enter Value"

        if (!value.isEmailValid()) {
            return "Enter Valid Email"
        }
        return ""
    }
}
