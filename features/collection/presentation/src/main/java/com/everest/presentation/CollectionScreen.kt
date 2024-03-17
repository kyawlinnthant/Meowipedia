package com.everest.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Switch
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
import com.everest.presentation.state.CollectionUiState
import com.everest.presentation.state.CollectionViewModelUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionScreen(
    state: CollectionViewModelUiState,
    isShow: Boolean,
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
            Column {
                Row {
                    Text(text = stringResource(id = R.string.own_collection))
                    Switch(
                        checked = isShow,
                        onCheckedChange = { value ->
                            onAction(CollectionAction.ShowOwnCollection(value))
                        },
                    )
                }
                when (state) {
                    is CollectionViewModelUiState.ListState -> CollectionView(state = state.state)

                    is CollectionViewModelUiState.OwnCollectionState -> CollectionView(state = state.state)
                }
            }
        }
    }
}

@Composable
fun CollectionView(
    state: CollectionUiState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        when (state) {
            is CollectionUiState.Error -> Text("Error")
            CollectionUiState.Loading -> CircularProgressIndicator()
            is CollectionUiState.HasData -> SuccessState(list = state.collectionList)
        }
    }
}

@Composable
fun SuccessState(
    list: List<CollectionVO>,
    modifier: Modifier = Modifier
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
