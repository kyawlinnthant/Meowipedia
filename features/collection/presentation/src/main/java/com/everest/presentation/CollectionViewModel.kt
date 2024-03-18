package com.everest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.domain.GetCollection
import com.everest.navigation.navigator.AppNavigator
import com.everest.presentation.state.CollectionViewModelState
import com.everest.util.result.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val getCollection: GetCollection,
    private val appNavigator: AppNavigator
) : ViewModel() {
    private val _isShowOwnCollection = MutableStateFlow(false)
    val isShowOwnCollection = _isShowOwnCollection.asStateFlow()

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
            is CollectionAction.Upload -> TODO()
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
