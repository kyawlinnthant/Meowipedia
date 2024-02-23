package com.everest.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.everest.upload.presentation.R
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadScreen(
    state: UploadUiState,
    onAction: (UploadAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.upload)) },
                actions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_input_add),
                            contentDescription = null
                        )
                    }

                },
            )

        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            when (state) {
                is UploadUiState.Error -> Text("Error")
                UploadUiState.Loading -> CircularProgressIndicator()
                UploadUiState.Normal -> NormalState(onAction)
                UploadUiState.Success -> SuccessState(onAction)
            }
        }
    }
}

@Composable
fun SuccessState(onAction: (UploadAction) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Success")
        Button(onClick = { onAction(UploadAction.Reset) }) {
            Text(text = "Select File")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NormalState(onAction: (UploadAction) -> Unit) {
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }
    var uriPath by remember { mutableStateOf<String?>(null) }
    var tempFile by remember { mutableStateOf<File?>(null) }


    val chooseFileLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        selectedFileUri = uri
        selectedFileUri?.path?.let {
            uriPath = it
            tempFile = File(it)
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = rememberAsyncImagePainter(model = selectedFileUri),
            contentDescription = null,
            modifier = Modifier
                .size(36.dp)
        )
        Image(
            painter = rememberAsyncImagePainter(model = tempFile),
            contentDescription = null,
            modifier = Modifier
                .size(36.dp)
        )
        Button(onClick = { chooseFileLauncher.launch("image/*") }) {
            Text(text = "Select File")
        }

        Spacer(modifier = Modifier.height(16.dp))

        selectedFileUri?.let { uri ->
            Text(text = "Selected File: $uri")
        }

        Button(onClick = {
            uriPath?.let { path ->
                println(">>>> $path")
                onAction(UploadAction.Upload(File(path)))
            }
        }) {
            Text(text = "Upload File")
        }
    }
}
