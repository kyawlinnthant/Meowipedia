package com.everest.presentation.meow.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun GalleryFirstTimeShimmer(
    modifier: Modifier = Modifier,
    paddingValue: PaddingValues
) {
    Box(
        modifier = modifier.fillMaxSize().padding(paddingValue),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
