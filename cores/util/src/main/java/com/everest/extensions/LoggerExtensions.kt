package com.everest.extensions

import timber.log.Timber

fun Any.log(message: String) {
    Timber.tag(this.javaClass.simpleName).d(message)
}
