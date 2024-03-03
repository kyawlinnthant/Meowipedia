package com.everest.presentation.register

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegisterScreen(
    state: RegisterUIState,
    onAction: (RegisterAction) -> Unit
) {
    val context = LocalContext.current
    val mail = rememberTextFieldState()
    val password = rememberTextFieldState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField2(state = mail)
        BasicTextField2(state = password)
        print(">>> $state")
        when (state) {
            is RegisterUIState.Error ->  Text("Error", color = Color.Red)

            RegisterUIState.Loading -> Text("Loading", color = Color.Red)
            else -> DefaultView {
                onAction(RegisterAction.Register("minthant12321@gmail.com", "123456"))
            }
        }
    }
}


@Composable
fun DefaultView(onAction: (RegisterAction) -> Unit) {
    Column {
        Button(onClick = {
            onAction(RegisterAction.Register("minthant12321@gmail.com", "123456"))
        }, shape = CutCornerShape(10)) {
            Text(text = "Register")
        }
    }
}
