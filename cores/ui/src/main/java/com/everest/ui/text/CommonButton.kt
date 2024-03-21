package com.everest.ui.text

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CommonButton(modifier: Modifier, title: String, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = {
            onClick()
        },
        shape = CutCornerShape(10)
    ) {
        CommonButtonText(text = title)
    }
}
