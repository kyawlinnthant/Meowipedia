package com.everest.presentation.meow.view.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.everest.theme.MeowipediaTheme
import com.everest.theme.dimen
import com.everest.type.ThemeType
import com.everest.ui.shimmer.ShimmerView

@Composable
fun MeowsExpandedLoading(
    modifier: Modifier = Modifier
) {
    val items = listOf(
        2160f / 1284f,
        1250f / 2064f,
        973f / 973f,
        1024f / 817f,
        900f / 1137f,
        1024f / 768f,
        881f / 1280f,
        944f / 1024f,
        1280f / 1290f,
        1250f / 702f,
        1280f / 720f,
        1110f / 730f,
        1250f / 2014f,
        1280f / 960f,
        3888f / 2592f,
        1250f / 702f,
        1920f / 902f,
        1200f / 901f,
        640f / 960f,
        1366f / 768f,
        1265f / 2309f,
        857f / 1298f,
        908f / 565f,
        1234f / 897f
    )

    ShimmerView { brush ->

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            modifier = modifier.fillMaxSize(),
            verticalItemSpacing = MaterialTheme.dimen.small,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.small),
            state = rememberLazyStaggeredGridState()
        ) {
            items(
                items = items
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
            MeowsExpandedLoading()
        }
    }
}
