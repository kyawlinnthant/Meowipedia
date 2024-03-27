package com.everest.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.everest.util.result.NetworkError

class NetworkErrorPreviewParameter : PreviewParameterProvider<NetworkError> {
    override val values: Sequence<NetworkError>
        get() = sequenceOf(
            NetworkError.NoInternet,
            NetworkError.SomethingWrong,
            NetworkError.Dynamic(message = "This is dynamic error message from server")
        )
}
