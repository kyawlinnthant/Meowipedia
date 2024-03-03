package com.everest.presentation.register

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everest.ui.textfield.CommonTextField

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    state: RegisterUIState,
    snackbarHostState: SnackbarHostState,
    mail: TextFieldState,
    password: TextFieldState,
    onAction: (RegisterAction) -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(title = { Text(text = "Register") })
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CommonTextField(mail)
            Spacer(modifier = Modifier.height(16.dp))
            CommonTextField(password)
            Spacer(modifier = Modifier.height(16.dp))
            when (state) {
                RegisterUIState.Loading -> CircularProgressIndicator(
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                )

                else -> DefaultView(
                    onAction = {
                        onAction(RegisterAction.Register)
                    }, title = "Register"
                )
            }
        }
    }

}


@Composable
fun DefaultView(onAction: (RegisterAction) -> Unit, title: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                onAction(RegisterAction.Register)
            },
            shape = CutCornerShape(10),
        ) {
            Text(text = title)
        }
    }
}
