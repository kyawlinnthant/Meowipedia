package com.everest.ui.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.everest.network.NetworkError
import com.everest.ui.R

@Composable
fun asErrorMessage(error: NetworkError): String {
    return when (error) {
        is NetworkError.Dynamic -> error.message
        NetworkError.NoInternet -> stringResource(id = R.string.no_internet)
        NetworkError.SomethingWrong -> stringResource(id = R.string.something_wrong)
    }
}
