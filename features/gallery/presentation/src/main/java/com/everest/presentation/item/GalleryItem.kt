package com.everest.presentation.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.everest.domain.model.Gallery
import com.everest.gallery.presentation.R


@Composable
fun GalleryItem(
    index: Int,
    gallery: Gallery,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.BottomCenter
    ) {

        AsyncImage(
            model = gallery.photo,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            placeholder = painterResource(id = R.drawable.placeholder)
        )
        Text(
            text = "${index + 1}  = ${gallery.id}",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

    }
}
