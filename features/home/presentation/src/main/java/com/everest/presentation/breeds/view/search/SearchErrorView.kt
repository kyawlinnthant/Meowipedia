package com.everest.presentation.breeds.view.search

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.everest.ui.text.asErrorMessage
import com.everest.util.result.NetworkError

@Composable
fun SearchErrorView(
    modifier: Modifier = Modifier,
    error: NetworkError
) {
    val message = asErrorMessage(error = error)

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = message)
    }
}
