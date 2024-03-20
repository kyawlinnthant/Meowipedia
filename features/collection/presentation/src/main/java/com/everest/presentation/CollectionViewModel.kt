package com.everest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.everest.domain.GetCollection
import com.everest.domain.UploadFile
import com.everest.navigation.navigator.AppNavigator
import com.everest.presentation.state.CollectionViewModelState
import com.everest.presentation.state.UploadUiState
import com.everest.util.result.DataResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val getCollection: GetCollection,
    private val uploadFile: UploadFile,
    private val appNavigator: AppNavigator
) : ViewModel() {
    private val _isShowOwnCollection = MutableStateFlow(false)
    val isShowOwnCollection = _isShowOwnCollection.asStateFlow()

    private val _uploadUiState = MutableStateFlow(UploadUiState())
    val uploadUiState = _uploadUiState.asStateFlow()

    fun getCollection() {
        viewModelScope.launch {
            getCollection.invoke().collectLatest { data ->
                _vmState.update { state ->
                    state.copy(
                        firebaseUid = firebaseAuth.uid ?: "",
                        collectionList = flow {
                            emit(data)
                        }.cachedIn(viewModelScope)
                    )
                }
            }
        }
    }

    private val _vmState = MutableStateFlow(CollectionViewModelState())
    val uiState = _vmState.map(CollectionViewModelState::asListState).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = _vmState.value.asListState()
    )

    val ownCollectionUiState = _vmState.map(CollectionViewModelState::asOwnCollectionState).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = _vmState.value.asOwnCollectionState()
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
                            message = ""
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
                    showLoading = true
                )
            }
            when (val response = uploadFile(file = file)) {
                is DataResult.Failed -> {
                    _uploadUiState.update { state ->
                        state.copy(
                            showLoading = false,
                            message = "File Upload Failed"
                        )
                    }
                }

                is DataResult.Success -> {
                    _uploadUiState.update { state ->
                        state.copy(
                            showLoading = false,
                            message = "File Upload Success"
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
