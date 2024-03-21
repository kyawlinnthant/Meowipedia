package com.everest.ui.text

import android.content.Context
import com.everest.ui.R
import com.everest.util.result.NetworkError

fun Context.nonComposable(networkError: NetworkError): String =
    when (networkError) {
        is NetworkError.Dynamic -> networkError.message
        NetworkError.NoInternet -> this.getString(R.string.no_internet)
        NetworkError.SomethingWrong -> this.getString(R.string.no_internet)
    }
