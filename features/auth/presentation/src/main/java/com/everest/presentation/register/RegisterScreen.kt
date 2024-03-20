package com.everest.presentation.register

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.everest.auth.presentation.R
import com.everest.ui.item.InputErrorItem
import com.everest.ui.textfield.CommonSecureTextField
import com.everest.ui.textfield.CommonTextField

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    state: RegisterUIState,
    snackbarHostState: SnackbarHostState,
    registerUserInfoState: RegisterUserInfoState,
    onAction: (RegisterAction) -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(title = { Text(text = "Register") })
        }
    ) {
        Box(modifier = Modifier.background(color = Color.Red)) {
            Image(
                painterResource(R.drawable.cat),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CommonTextField(
                    registerUserInfoState.mailTextFieldState,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                )
                InputErrorItem(
                    title = registerUserInfoState.mailErrorMessage,
                    show = registerUserInfoState.mailErrorMessage.isNotEmpty()
                )
                Spacer(modifier = Modifier.height(16.dp))
                CommonSecureTextField(registerUserInfoState.passwordTextFieldState)
                InputErrorItem(
                    title = registerUserInfoState.passwordErrorMessage,
                    show = registerUserInfoState.passwordErrorMessage.isNotEmpty()
                )
                Spacer(modifier = Modifier.height(16.dp))
                CommonSecureTextField(registerUserInfoState.confirmPasswordTextFieldState)
                InputErrorItem(
                    title = registerUserInfoState.confirmPasswordErrorMessage,
                    show = registerUserInfoState.confirmPasswordErrorMessage.isNotEmpty()
                )

                when (state) {
                    RegisterUIState.Loading -> CircularProgressIndicator(
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp)
                    )

                    else -> DefaultView(
                        onAction = {
                            onAction(RegisterAction.Register)
                        },
                        title = "Register"
                    )
                }
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
            shape = CutCornerShape(10)
        ) {
            Text(text = title)
        }
    }
}
