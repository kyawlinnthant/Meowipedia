package com.everest.presentation

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.everest.domain.model.CollectionVO
import com.everest.file.utils.FileUtils.getFileFromUri
import com.everest.presentation.state.UploadUiState
import com.everest.theme.dimen
import com.everest.ui.R
import com.everest.ui.dialog.LoadingDialog
import com.everest.ui.shimmer.ShimmerBrush
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionScreen(
    ownCollectionList: LazyPagingItems<CollectionVO>,
    collectionList: LazyPagingItems<CollectionVO>,
    isShowOwnCollection: Boolean,
    dialogUiState: UploadUiState,
    onAction: (CollectionAction) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val context = LocalContext.current
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }

    dialogUiState.apply {
        if (this.message.isNotEmpty()) {
            Toast.makeText(context, this.message, Toast.LENGTH_LONG).show()
            onAction(CollectionAction.DismissDialog)
        }
    }

    val chooseFileLauncher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
        uri?.let {
            selectedFileUri = it
            val file = getFileFromUri(context, it)
            file?.let { f ->
                onAction(CollectionAction.Upload(f))
            }
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.news_feed)) },
            navigationIcon = {
                IconButton(onClick = { onAction(CollectionAction.Back) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = "Back"
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    chooseFileLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.upload),
                        contentDescription = null
                    )
                }
            }
        )
    }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(
                        horizontal = MaterialTheme.dimen.base2x
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.own_collection),
                        modifier = Modifier.weight(1f)
                    )
                    Switch(checked = isShowOwnCollection, onCheckedChange = { value ->
                        onAction(CollectionAction.ShowOwnCollection(value))
                    })
                }

                collectionList.apply {
                    when (collectionList.loadState.refresh) {
                        LoadState.Loading -> {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        is LoadState.Error -> {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(onClick = { retry() }) {
                                    Text(text = "Error")
                                }
                            }
                        }

                        else -> {
                            SuccessState(
                                collectionList = if (isShowOwnCollection) {
                                    ownCollectionList
                                } else {
                                    collectionList
                                },
                                lazyListState = lazyListState
                            )
                        }
                    }
                }
            }

            if (dialogUiState.showLoading) {
                LoadingDialog(onDismiss = {
                    onAction(CollectionAction.DismissDialog)
                })
            }
        }
    }
}

@Composable
fun SuccessState(
    collectionList: LazyPagingItems<CollectionVO>,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState
) {
    LazyColumn(
        modifier = modifier,
        state = lazyListState
    ) {
        items(
            count = collectionList.itemCount,
            key = (collectionList.itemKey { it.id })
        ) { index ->
            val currentVo = collectionList[index]
            currentVo?.let {
                CollectionItem(it, modifier)
            }
        }
        if (collectionList.loadState.append == LoadState.Loading) {
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.dimen.base2x)
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun CollectionItem(
    collectionVO: CollectionVO,
    modifier: Modifier
) {
    val brush = ShimmerBrush()

    val context = LocalContext.current
    val ratio = collectionVO.width.toFloat() / collectionVO.height.toFloat()
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
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(collectionVO.url).crossfade(true).build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
        IconButton(
            onClick = {
                downloadImage(
                    context,
                    collectionVO.url
                )
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.download),
                contentDescription = null
            )
        }
    }
}

fun downloadImage(context: Context, imageUrl: String) {
    val request = DownloadManager.Request(Uri.parse(imageUrl))
        .setTitle("Image Download")
        .setDescription("Downloading")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Meow-${System.currentTimeMillis()}.jpg")

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    downloadManager.enqueue(request)
}
