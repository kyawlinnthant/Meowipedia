package com.everest.ui.button

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.everest.ui.R
import com.everest.ui.preview.LightModePreview
import com.everest.ui.preview.NightModePreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BackButton(
    onBackClick: () -> Unit = {}
) {
    var enabled by remember {
        mutableStateOf(true)
    }
    val scope = rememberCoroutineScope()
    IconButton(
        onClick = {
            scope.launch {
                enabled = false
                onBackClick()
                delay(500L)
                enabled = true
            }
        },
        enabled = enabled
    ) {
        Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24), contentDescription = "Back Arrow")
    }
}

@Composable
@Preview
private fun LightPreview() {
    LightModePreview {
        BackButton()
    }
}
@Composable
@Preview
private fun NightPreview() {
    NightModePreview {
        BackButton()
    }
}
