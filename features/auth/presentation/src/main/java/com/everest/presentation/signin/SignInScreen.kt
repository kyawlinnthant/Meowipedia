package com.everest.presentation.signin

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.everest.extensions.log
import com.everest.navigation.Screens
import com.everest.theme.WindowSize
import com.everest.theme.WindowType
import com.everest.ui.R
import com.everest.ui.text.CommonButton
import com.everest.ui.text.nonComposable
import com.everest.ui.textfield.CommonSecureTextField
import com.everest.ui.textfield.CommonTextField
import com.everest.ui.textfield.InputSecureTextFieldWithMessage
import com.everest.ui.textfield.InputTextFieldWithMessage
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    windowSize: WindowSize,
    signInUiState: SignInUIState,
    signUserInfoState: SignInUserInfoState,
    snackbarHostState: SnackbarHostState,
    mail: TextFieldState,
    password: TextFieldState,
    onAction: (SignInAction) -> Unit
) {
    val signInViewModel: SignInViewModel = hiltViewModel()
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        signInViewModel.signInEvent.collectLatest { event ->
            when (event) {
                SignInEvent.DefaultView -> log("Default View")
                is SignInEvent.ShowSnack -> {
                    snackbarHostState.showSnackbar(
                        message = context.nonComposable(event.error)
                    )
                }
            }
        }
    }
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }, topBar = {
        TopAppBar(title = { Text(text = "Sign In") })
    }) {
        Box(modifier = Modifier.padding(it)) {
            when (windowSize.width) {
                WindowType.Compact -> SignInCompact(
                    signInUiState = signInUiState,
                    signUserInfoState = signUserInfoState,
                    mail = mail,
                    password = password,
                    onAction = onAction
                )

                else -> SignInTablet(
                    signInUiState = signInUiState,
                    signUserInfoState = signUserInfoState,
                    mail = mail,
                    password = password,
                    onAction = onAction
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignInCompact(
    signInUiState: SignInUIState,
    signUserInfoState: SignInUserInfoState,
    mail: TextFieldState,
    password: TextFieldState,
    onAction: (SignInAction) -> Unit
) {
    Box {
        Image(
            painterResource(R.drawable.cat),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputTextFieldWithMessage(
                hint = stringResource(id = R.string.mail),
                state = mail,
                errorMessage = signUserInfoState.mailErrorMessage
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputSecureTextFieldWithMessage(
                hint = stringResource(id = R.string.password),
                state = password,
                errorMessage = signUserInfoState.passwordErrorMessage
            )
            Spacer(modifier = Modifier.height(16.dp))
            when (signInUiState) {
                SignInUIState.Loading -> CircularProgressIndicator(
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                )

                else -> DefaultSignInView(
                    onAction = onAction,
                )
            }

        }
    }
}

@Composable
fun DefaultSignInView(onAction: (SignInAction) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        CommonButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            title = stringResource(id = R.string.sign_in),
            onClick = {
                onAction(SignInAction.SignIn)
            }
        )
        CommonButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            title = stringResource(id = R.string.register),
            onClick = {
                onAction(SignInAction.Navigate(Screens.Register.route))
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignInTablet(
    signInUiState: SignInUIState,
    signUserInfoState: SignInUserInfoState,
    mail: TextFieldState,
    password: TextFieldState,
    onAction: (SignInAction) -> Unit
) {
    Row {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CommonTextField(mail)
            Spacer(modifier = Modifier.height(16.dp))
            CommonSecureTextField(password)
            Spacer(modifier = Modifier.height(16.dp))
            when (signInUiState) {
                SignInUIState.Loading -> CircularProgressIndicator(
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                )

                else -> DefaultSignInView(
                    onAction = {
                        onAction(SignInAction.SignIn)
                    },
                )
            }
        }
        Image(
            painterResource(R.drawable.cat),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview(device = Devices.PIXEL_5)
@Composable
fun PreviewSignCompact() {
    SignInScreen(
        windowSize = WindowSize(WindowType.Compact, WindowType.Compact),
        signInUiState = SignInUIState.DefaultView,
        signUserInfoState = SignInUserInfoState(),
        snackbarHostState = SnackbarHostState(),
        mail = TextFieldState(),
        password = TextFieldState()
    ) {
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(device = Devices.TABLET)
@Composable
fun PreviewSignMedium() {
    SignInScreen(
        windowSize = WindowSize(WindowType.Medium, WindowType.Medium),
        signInUiState = SignInUIState.DefaultView,
        snackbarHostState = SnackbarHostState(),
        signUserInfoState = SignInUserInfoState(),
        mail = TextFieldState(),
        password = TextFieldState()
    ) {
    }
}
