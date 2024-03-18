package com.everest.presentation

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.everest.collection.presentation.R
import com.everest.domain.model.CollectionVO
import com.everest.file.utils.FileUtils.getFileFromUri
import com.everest.presentation.state.CollectionUiState
import com.everest.presentation.state.CollectionViewModelUiState
import com.everest.theme.dimen
import com.everest.ui.dialog.LoadingDialog
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionScreen(
    state: CollectionViewModelUiState,
    isShow: Boolean,
    isUploading: Boolean,
    filePickStatus: String?,
    onAction: (CollectionAction) -> Unit
) {
    val context = LocalContext.current
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }

    LaunchedEffect(filePickStatus) {
        filePickStatus?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
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
            title = { Text(text = stringResource(id = R.string.collection)) },
            navigationIcon = {
                IconButton(onClick = { onAction(CollectionAction.Back) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_arrow),
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
                .fillMaxSize(),
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(
                        horizontal = MaterialTheme.dimen.base2x
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.own_collection), modifier = Modifier.weight(1f)
                    )
                    Switch(checked = isShow, onCheckedChange = { value ->
                        onAction(CollectionAction.ShowOwnCollection(value))
                    })
                }
                Box(modifier = Modifier.weight(1f)) {
                    when (state) {
                        is CollectionViewModelUiState.ListState -> CollectionView(state = state.state)

                        is CollectionViewModelUiState.OwnCollectionState -> CollectionView(state = state.state)
                    }
                }
            }

            if (isUploading) {
                LoadingDialog(onDismiss = {
                    onAction(CollectionAction.DismissDialog)
                })
            }
        }
    }
}

@Composable
fun CollectionView(
    state: CollectionUiState, modifier: Modifier = Modifier
) {
    when (state) {
        is CollectionUiState.Error -> Text("Error")

        CollectionUiState.Loading -> Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {

            CircularProgressIndicator()
        }

        is CollectionUiState.HasData -> SuccessState(list = state.collectionList)
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
            CollectionItem(currentVo, modifier)
        }
    }
}

@Composable
fun CollectionItem(
    collectionVO: CollectionVO,
    modifier: Modifier
) {
    val context = LocalContext.current;
    Box {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(collectionVO.url).crossfade(true).build(), contentDescription = "", contentScale = ContentScale.Crop,
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
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Meow-${Calendar.getInstance()}.jpg")

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    downloadManager.enqueue(request)
}
