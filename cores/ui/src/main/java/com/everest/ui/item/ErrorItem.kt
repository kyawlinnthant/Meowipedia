package com.everest.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.everest.theme.MeowipediaTheme
import com.everest.theme.dimen
import com.everest.type.ThemeType
import com.everest.ui.R

@Composable
fun ErrorItem(
    modifier: Modifier = Modifier,
    message: String,
    isGrid: Boolean = false,
    onRetry: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimen.base2x),
        contentAlignment = Alignment.Center
    ) {
        if (isGrid) {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    modifier = modifier.weight(
                        weight = 1f,
                        fill = false
                    )

                )
                Spacer(modifier = modifier.height(MaterialTheme.dimen.base2x))
                OutlinedButton(
                    onClick = onRetry
                ) {
                    Text(text = stringResource(id = R.string.retry))
                }
            }
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    modifier = modifier.weight(
                        weight = 1f,
                        fill = false
                    )

                )
                Spacer(modifier = modifier.width(MaterialTheme.dimen.base2x))
                OutlinedButton(
                    onClick = onRetry
                ) {
                    Text(text = stringResource(id = R.string.retry))
                }
            }
        }
    }
}

@Composable
@Preview
private fun ListPreview() {
    MeowipediaTheme(appTheme = ThemeType.NightType, dynamicColor = true) {
        Surface {
            ErrorItem(message = "No Internet! Please connect wifi or mobile data!") {
            }
        }
    }
}

@Composable
@Preview
private fun GridPreview() {
    MeowipediaTheme(appTheme = ThemeType.NightType, dynamicColor = true) {
        Surface {
            ErrorItem(message = "No Internet! Please connect wifi or mobile data!", isGrid = true) {
            }
        }
    }
}
