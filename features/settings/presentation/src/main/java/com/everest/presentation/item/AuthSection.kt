package com.everest.presentation.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.everest.navigation.Screens
import com.everest.presentation.SettingsAction
import com.everest.theme.dimen
import com.everest.ui.R

@Composable
fun AuthSection(
    modifier: Modifier = Modifier,
    isLogin: Boolean,
    onAction: (SettingsAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.dimen.base2x,
                vertical = MaterialTheme.dimen.base
            )
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        if (!isLogin) {
            AuthSectionItem(title = stringResource(id = R.string.login)) {
                onAction(SettingsAction.Navigate(Screens.AuthGraph.route))
            }
        } else {
            AuthSectionItem(title = stringResource(id = R.string.logout)) {
                onAction(SettingsAction.Logout)
            }
        }
    }
}
