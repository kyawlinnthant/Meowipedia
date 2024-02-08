package com.everest.categories.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.categories.domain.usecase.FetchCategories
import com.everest.util.result.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val fetchCategories: FetchCategories
) : ViewModel() {
    private val vmState = MutableStateFlow(CategoriesViewModelState())
    val uiState = vmState
        .map(CategoriesViewModelState::asUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asUiState(),
        )

    fun fetch() {
        vmState.update { state ->
            state.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            when (val response = fetchCategories()) {
                is DataResult.Failed -> vmState.update { state ->
                    state.copy(
                        isError = response.error,
                        isLoading = false
                    )
                }

                is DataResult.Success -> vmState.update { state ->
                    state.copy(
                        categories = response.data,
                        isLoading = false
                    )
                }
            }
        }
    }
}