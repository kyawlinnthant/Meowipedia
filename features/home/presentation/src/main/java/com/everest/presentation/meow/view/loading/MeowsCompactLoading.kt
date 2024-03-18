package com.everest.presentation.meow.view.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.everest.theme.MeowipediaTheme
import com.everest.type.ThemeType
import com.everest.ui.shimmer.ShimmerView

@Composable
fun MeowsCompactLoading(
    modifier: Modifier = Modifier
) {
    val items = listOf(
        2160f / 1284f,
        973f / 973f,
        1024f / 817f,
        900f / 1137f,
        1024f / 768f,
        973f / 973f,
        1024f / 817f,
        900f / 1137f,
        1024f / 768f
    )

    ShimmerView { brush ->

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            state = rememberLazyListState()
//            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            items(
                items = items,
                key = { it }
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .aspectRatio(
                            ratio = it,
                            matchHeightConstraintsFirst = true
                        )
                        .background(brush = brush)
                )
            }

            item {
                Spacer(
                    modifier = modifier
                        .navigationBarsPadding()
                        .padding(bottom = 80.dp)
                )
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    MeowipediaTheme(appTheme = ThemeType.NightType, dynamicColor = true) {
        Surface {
            MeowsCompactLoading()
        }
    }
}
