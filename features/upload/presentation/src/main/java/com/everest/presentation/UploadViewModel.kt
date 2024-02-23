package com.everest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.domain.UploadFile
import com.everest.navigation.navigator.AppNavigator
import com.everest.util.result.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class UploadViewModel @Inject constructor(
    private val uploadFile: UploadFile,
    private val appNavigator: AppNavigator
) : ViewModel() {

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
        }
    }
}
