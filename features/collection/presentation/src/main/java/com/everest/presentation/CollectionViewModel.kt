package com.everest.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.domain.GetCollection
import com.everest.navigation.navigator.AppNavigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class CollectionViewModel @Inject constructor(
    private val getCollection: GetCollection,
    private val appNavigator: AppNavigator,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val vmState = MutableStateFlow(CollectionViewModelState())
    val uiState = vmState.map(CollectionViewModelState::asUiState).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = vmState.value.asUiState()
    )

    fun onAction(action: CollectionAction) {
        when (action) {
            CollectionAction.Back -> {
                appNavigator.back()
            }

            is CollectionAction.Navigate -> appNavigator.to(action.route)
        }
    }

}
