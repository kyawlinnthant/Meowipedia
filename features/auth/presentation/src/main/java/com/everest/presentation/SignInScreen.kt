package com.everest.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SignInScreen(
    state: SignInUIState,
    onAction: (SignInAction) -> Unit
) {
    Column {
        Button(onClick = {
            onAction(SignInAction.SignIn)
        }, shape = CutCornerShape(10)) {
            Text(text = "Sign In")
        }
        Button(onClick = {
            onAction(SignInAction.Register("minthant12321@gmail.com", "123456"))
        }, shape = CutCornerShape(10)) {
            Text(text = "Register")
        }
    }
}
