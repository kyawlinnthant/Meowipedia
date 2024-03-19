package com.everest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.everest.domain.GetCollection
import com.everest.domain.UploadFile
import com.everest.domain.model.CollectionVO
import com.everest.navigation.navigator.AppNavigator
import com.everest.presentation.state.CollectionViewModelState
import com.everest.presentation.state.UploadUiState
import com.everest.util.result.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
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


    private val _uploadUiState = MutableStateFlow(UploadUiState())
    val uploadUiState = _uploadUiState.asStateFlow()

    val collectionList = getCollection().cachedIn(viewModelScope)

    private val _vmState = MutableStateFlow(CollectionViewModelState())
    val uiState = _vmState.map(CollectionViewModelState::asUiState).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = _vmState.value.asUiState()
    )

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
                    _uploadUiState.update { state ->
                        state.copy(
                            showLoading = false,
                        )
                    }
                }
            }
        }
    }

    private fun fileUpload(file: File) {
        viewModelScope.launch {
            _uploadUiState.update { state ->
                state.copy(
                    showLoading = true,
                )
            }
            when (val response = uploadFile(file = file)) {
                is DataResult.Failed -> {
                    _uploadUiState.update { state ->
                        state.copy(
                            showLoading = false,
                            message = "File Upload Success",
                        )
                    }
                }

                is DataResult.Success -> {
                    _uploadUiState.update { state ->
                        state.copy(
                            showLoading = false,
                            message = "File Upload Failed",
                        )
                    }
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
