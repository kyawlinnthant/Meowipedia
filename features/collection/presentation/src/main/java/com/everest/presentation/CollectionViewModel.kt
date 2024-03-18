package com.everest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.domain.GetCollection
import com.everest.domain.UploadFile
import com.everest.navigation.navigator.AppNavigator
import com.everest.presentation.state.CollectionViewModelState
import com.everest.util.result.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val getCollection: GetCollection,
    private val uploadFile: UploadFile,
    private val appNavigator: AppNavigator
) : ViewModel() {
    private val _isShowOwnCollection = MutableStateFlow(false)
    val isShowOwnCollection = _isShowOwnCollection.asStateFlow()

    private val _isFileUploading = MutableSharedFlow<Boolean>()
    val isFileUploading: SharedFlow<Boolean> = _isFileUploading

    private val _fileUploadStatus = MutableSharedFlow<String>()
    val fileUploadStatus: SharedFlow<String> = _fileUploadStatus

    private val _vmState = MutableStateFlow(CollectionViewModelState())
    val uiState = _vmState.map(CollectionViewModelState::asUiState).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = _vmState.value.asUiState()
    )

    fun getCollection() {
        _vmState.update { state ->
            state.copy(
                ownState = state.ownState.copy(
                    isLoading = true
                ),
                listState = state.listState.copy(
                    isLoading = true
                )
            )
        }
        viewModelScope.launch {
            when (val response = getCollection.invoke()) {
                is DataResult.Failed -> _vmState.update { state ->
                    state.copy(
                        ownState = state.ownState.copy(
                            isError = response.error,
                            isLoading = false
                        ),
                        listState = state.listState.copy(
                            isError = response.error,
                            isLoading = false
                        )
                    )
                }

                is DataResult.Success -> {
                    _vmState.update { state ->
                        state.copy(
                            ownState = state.ownState.copy(
                                isLoading = false,
                                collectionList = response.data.filter { it.subId == "my-user-1234" }
                            ),
                            listState = state.listState.copy(
                                isLoading = false,
                                collectionList = response.data
                            )
                        )
                    }
                }
            }
        }
    }

    fun onAction(action: CollectionAction) {
        when (action) {
            CollectionAction.Back -> {
                appNavigator.back()
            }

            is CollectionAction.Navigate -> appNavigator.to(action.route)
            is CollectionAction.ShowOwnCollection -> showOwnCollection(action.isShow)
            is CollectionAction.Upload -> fileUpload(action.file)
            CollectionAction.DismissDialog -> {
                viewModelScope.launch {
                    _isFileUploading.emit(false)
                }
            }
        }
    }

    private fun fileUpload(file: File) {
        viewModelScope.launch {
            _isFileUploading.emit(true)
//            _vmState.update { state ->
//                state.copy(
//                    state = state.state.copy(
//                        isLoading = true
//                    )
//                )
//            }
            when (val response = uploadFile(file = file)) {
                is DataResult.Failed -> {
                    _isFileUploading.emit(false)
                    _fileUploadStatus.emit("File Upload Failed")
//                    vmState.update { state ->
//                        state.copy(
//                            state = state.state.copy(
//                                isError = response.error,
//                                isLoading = false
//                            )
//                        )
//                    }
                }

                is DataResult.Success -> {
                    _isFileUploading.emit(false)
                    _fileUploadStatus.emit("File Upload Success")
//                    vmState.update { state ->
//                        state.copy(
//                            state = state.state.copy(
//                                isSuccess = true
//                            )
//                        )
//                    }
                }
            }
        }
    }

    private fun showOwnCollection(isShow: Boolean) {
        _isShowOwnCollection.value = isShow
        _vmState.update { state ->
            state.copy(
                showOwnCollection = isShowOwnCollection.value
            )
        }
    }
}
