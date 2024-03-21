package com.everest.ui.item

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.everest.theme.dimen
import com.everest.ui.text.CommonText

@Composable
fun InputErrorItem(title: String, show: Boolean = false) {
    AnimatedVisibility(visible = show) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.dimen.base2x
                )
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            CommonText(
                title,
                textColor = Color.Gray,
                fontSize = MaterialTheme.dimen.hintTextSize
            )
        }
    }
}
