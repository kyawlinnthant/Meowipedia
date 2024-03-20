package com.everest.presentation.signin

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.everest.auth.presentation.R
import com.everest.navigation.Screens
import com.everest.presentation.register.DefaultView
import com.everest.theme.WindowSize
import com.everest.theme.WindowType
import com.everest.ui.item.InputErrorItem
import com.everest.ui.textfield.CommonSecureTextField
import com.everest.ui.textfield.CommonTextField

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    windowSize: WindowSize,
    state: SignInUIState,
    signUserInfoState: SignInUserInfoState,
    snackbarHostState: SnackbarHostState,
    mail: TextFieldState,
    password: TextFieldState,
    onAction: (SignInAction) -> Unit
) {
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }, topBar = {
        TopAppBar(title = { Text(text = "Sign In") })
    }) {
        Box(modifier = Modifier.padding(it)) {
            when (windowSize.width) {
                WindowType.Compact -> SignInCompact(
                    state = state,
                    signUserInfoState = signUserInfoState,
                    mail = mail,
                    password = password,
                    onAction = onAction
                )

                else -> SignInTablet(
                    state = state,
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
    state: SignInUIState,
    signUserInfoState: SignInUserInfoState,
    mail: TextFieldState,
    password: TextFieldState,
    onAction: (SignInAction) -> Unit
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
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CommonTextField(mail, stringResource(id = R.string.mail))
            InputErrorItem(
                title = signUserInfoState.mailErrorMessage,
                show = signUserInfoState.mailErrorMessage.isNotEmpty()
            )
            Spacer(modifier = Modifier.height(16.dp))
            CommonSecureTextField(password, stringResource(id = R.string.password))
            InputErrorItem(
                title = signUserInfoState.passwordErrorMessage,
                show = signUserInfoState.passwordErrorMessage.isNotEmpty()
            )
            Spacer(modifier = Modifier.height(16.dp))
            when (state) {
                SignInUIState.Loading -> CircularProgressIndicator(
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                )

                else -> DefaultView(
                    onAction = {
                        onAction(SignInAction.SignIn)
                    },
                    title = "Sign In"
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = {
                    onAction(SignInAction.Navigate(Screens.Register.route))
                },
                shape = CutCornerShape(10)
            ) {
                Text(text = "Register")
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignInTablet(
    state: SignInUIState,
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
            when (state) {
                SignInUIState.Loading -> CircularProgressIndicator(
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                )

                else -> DefaultView(
                    onAction = {
                        onAction(SignInAction.SignIn)
                    },
                    title = "Sign In"
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = {
                    onAction(SignInAction.Navigate(Screens.Register.route))
                },
                shape = CutCornerShape(10)
            ) {
                Text(text = "Register")
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
        state = SignInUIState.DefaultView,
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
        state = SignInUIState.DefaultView,
        snackbarHostState = SnackbarHostState(),
        signUserInfoState = SignInUserInfoState(),
        mail = TextFieldState(),
        password = TextFieldState()
    ) {
    }
}
