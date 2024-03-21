package com.everest.presentation.register

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.everest.extensions.log
import com.everest.ui.R
import com.everest.ui.text.CommonButton
import com.everest.ui.text.CommonText
import com.everest.ui.text.nonComposable
import com.everest.ui.textfield.InputSecureTextFieldWithMessage
import com.everest.ui.textfield.InputTextFieldWithMessage
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    registerUIState: RegisterUIState,
    snackbarHostState: SnackbarHostState,
    registerUserInfoState: RegisterUserInfoState,
    onAction: (RegisterAction) -> Unit
) {
    val context = LocalContext.current
    val vm: RegisterViewModel = hiltViewModel()
    LaunchedEffect(key1 = true) {
        vm.registerEvent.collectLatest { event ->
            when (event) {
                RegisterEvent.DefaultView -> log("Default View")
                is RegisterEvent.ShowSnack -> {
                    snackbarHostState.showSnackbar(
                        message = context.nonComposable(event.error)
                    )
                }
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(title = { CommonText(text = stringResource(id = R.string.register)) })
        }
    ) {
        RegisterContent(
            paddingValues = it,
            registerUserInfoState = registerUserInfoState,
            onAction = onAction,
            registerUIState = registerUIState
        )
    }
}

@Composable
fun RegisterContent(
    paddingValues: PaddingValues,
    registerUserInfoState: RegisterUserInfoState,
    registerUIState: RegisterUIState,
    onAction: (RegisterAction) -> Unit
) {
    Box {
        Image(
            painterResource(R.drawable.cat),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
        RegisterInfo(
            paddingValues = paddingValues,
            registerUserInfoState = registerUserInfoState,
            registerUIState = registerUIState,
            onAction = onAction
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegisterInfo(
    paddingValues: PaddingValues,
    registerUserInfoState: RegisterUserInfoState,
    registerUIState: RegisterUIState,
    onAction: (RegisterAction) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputTextFieldWithMessage(
            registerUserInfoState.mailTextFieldState,
            hint = stringResource(id = R.string.login),
            errorMessage = registerUserInfoState.mailErrorMessage
        )
        Spacer(modifier = Modifier.height(16.dp))
        InputSecureTextFieldWithMessage(
            registerUserInfoState.passwordTextFieldState,
            hint = stringResource(id = R.string.password),
            errorMessage = registerUserInfoState.passwordErrorMessage
        )
        Spacer(modifier = Modifier.height(16.dp))
        InputSecureTextFieldWithMessage(
            registerUserInfoState.confirmPasswordTextFieldState,
            hint = stringResource(id = R.string.password),
            errorMessage = registerUserInfoState.confirmPasswordErrorMessage
        )

        RegisterButtonState(
            registerUIState = registerUIState,
            onAction = onAction
        )
    }
}

@Composable
fun RegisterButtonState(
    registerUIState: RegisterUIState,
    onAction: (RegisterAction) -> Unit
) {
    when (registerUIState) {
        RegisterUIState.Loading -> CircularProgressIndicator(
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
        )

        else -> DefaultRegisterView(
            onAction = {
                onAction(RegisterAction.Register)
            },
            title = stringResource(id = R.string.register)
        )
    }
}

@Composable
fun DefaultRegisterView(onAction: (RegisterAction) -> Unit, title: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        CommonButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            title = title,
            onClick = {
                onAction(RegisterAction.Register)
            }
        )
    }
}
