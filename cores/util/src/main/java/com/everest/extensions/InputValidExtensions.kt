package com.everest.extensions

import android.util.Patterns

fun String.isEmailValid(): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(this).matches()
}
