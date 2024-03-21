package com.everest.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun String.getLocaleFromLanguageTags() = this.substringBefore('-')

fun Activity.hideKeyboard() {
    val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}
