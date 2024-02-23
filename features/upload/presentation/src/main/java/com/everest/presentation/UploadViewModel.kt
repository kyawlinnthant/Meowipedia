package com.everest.presentation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.domain.UploadFile
import com.everest.file.utils.FileResource
import com.everest.navigation.navigator.AppNavigator
import com.everest.util.result.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(
    private val uploadFile: UploadFile,
    private val appNavigator: AppNavigator,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    /**
     * We keep the current media [Uri] in the savedStateHandle to re-render it if there is a
     * configuration change
     */
    val selectedFile: StateFlow<FileResource?> =
        savedStateHandle.getStateFlow(SELECTED_FILE_KEY, null)
//        savedStateHandle.getLiveData(SELECTED_FILE_KEY)

    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow: SharedFlow<String> = _errorFlow

    companion object {
        private val TAG = this::class.java.simpleName
        const val SELECTED_FILE_KEY = "selectedFile"
    }

    private val vmState = MutableStateFlow(UploadViewModelState())
    val uiState = vmState
        .map(UploadViewModelState::asUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asUiState()
        )

    fun onAction(action: UploadAction) {
        when (action) {
            UploadAction.Back -> {
                appNavigator.back()
            }

            UploadAction.Reset -> {
                vmState.update {
                    it.copy(
                        state = UploadState(isNormal = true)
                    )
                }
            }

            is UploadAction.Upload -> {
                viewModelScope.launch {
                    vmState.update { state ->
                        state.copy(
                            state = state.state.copy(
                                isLoading = true
                            )
                        )
                    }
                    when (val response = uploadFile(file = action.file)) {
                        is DataResult.Failed -> {
                            vmState.update { state ->
                                state.copy(
                                    state = state.state.copy(
                                        isError = response.error,
                                        isLoading = false
                                    )
                                )
                            }
                        }

                        is DataResult.Success -> {
                            vmState.update { state ->
                                state.copy(
                                    state = state.state.copy(
                                        isSuccess = true
                                    )
                                )
                            }
                        }
                    }
                }
            }

            is UploadAction.FileSelect -> {
//                viewModelScope.launch {
//                    savedStateHandle[SELECTED_FILE_KEY] = SafUtils.getResourceByUri(context, action.uri)
//
//                    try {
//                        savedStateHandle[SELECTED_FILE_KEY] = SafUtils.getResourceByUri(context, action.uri)
//                    } catch (e: Exception) {
//                        Log.e(TAG, e.printStackTrace().toString())
//                        _errorFlow.emit("Couldn't load ${action.uri}")
//                    }
//                }
            }
        }
    }
}
