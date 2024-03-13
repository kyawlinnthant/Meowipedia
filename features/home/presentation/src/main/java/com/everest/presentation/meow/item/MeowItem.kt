package com.everest.presentation.meow.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.everest.domain.model.meow.MeowVo
import com.everest.ui.shimmer.ShimmerBrush

@Composable
fun MeowItem(
    meowVo: MeowVo,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    val brush = ShimmerBrush()
    val ratio = meowVo.width.toFloat() / meowVo.height.toFloat()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(
                ratio = ratio,
                matchHeightConstraintsFirst = true
            )
            .drawBehind {
                drawRect(
                    brush = brush
                )
            }
            .clickable {
                onItemClick()
            },
        contentAlignment = Alignment.BottomCenter

    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(meowVo.photo)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()

        )
    }
}
