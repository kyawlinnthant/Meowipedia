package com.everest.ui.textfield

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.runtime.Composable
import com.everest.ui.item.InputErrorItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InputSecureTextFieldWithMessage(
    state: TextFieldState,
    hint: String = "",
    errorMessage: String = ""
) {
    Column {
        CommonSecureTextField(
            state,
            hint = hint
        )
        InputErrorItem(
            title = errorMessage,
            show = errorMessage.isNotEmpty()
        )
    }
}
