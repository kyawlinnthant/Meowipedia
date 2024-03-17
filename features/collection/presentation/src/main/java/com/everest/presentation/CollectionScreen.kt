package com.everest.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.everest.collection.presentation.R
import com.everest.domain.CollectionVO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionScreen(
    state: CollectionUiState,
    onAction: (CollectionAction) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.collection)) },
                actions = {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_input_add),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                is CollectionUiState.Error -> Text("Error")
                CollectionUiState.Loading -> CircularProgressIndicator()
                is CollectionUiState.Success -> SuccessState(
                    list = state.data,
                    onAction = onAction
                )
            }
        }
    }
}


@Composable
fun SuccessState(
    list: List<CollectionVO>,
    modifier: Modifier = Modifier,
    onAction: (CollectionAction) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(count = list.size, key = { index -> list[index].id }) { index ->
            val currentVo = list[index]
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(currentVo.url)
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
}
