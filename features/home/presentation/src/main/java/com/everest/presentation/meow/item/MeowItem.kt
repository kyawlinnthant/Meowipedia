package com.everest.presentation.meow.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.everest.domain.model.meow.MeowVo
import com.everest.home.presentation.R

@Composable
fun MeowItem(
    index: Int,
    meowVo: MeowVo,
    modifier: Modifier = Modifier
) {
    val ratio = meowVo.width.toFloat() / meowVo.height.toFloat()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(
                ratio = ratio,
                matchHeightConstraintsFirst = true
            ),
        contentAlignment = Alignment.BottomCenter

    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(meowVo.photo)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.baseline_image_24),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),

        )

        Text(
            text = "${index + 1} : ${meowVo.id}, ${meowVo.width}, ${meowVo.height}",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}
