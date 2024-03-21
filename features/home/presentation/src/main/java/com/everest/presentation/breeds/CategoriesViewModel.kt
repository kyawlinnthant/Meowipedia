package com.everest.presentation.breeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.everest.domain.usecase.GetBreeds
import com.everest.domain.usecase.SearchBreeds
import com.everest.navigation.navigator.AppNavigator
import com.everest.presentation.breeds.state.CategoriesViewModelState
import com.everest.util.result.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    getBreeds: GetBreeds,
    private val searchBreeds: SearchBreeds,
    private val navigator: AppNavigator
) : ViewModel() {

    val categories = getBreeds().cachedIn(viewModelScope)
    private val _vmState = MutableStateFlow(CategoriesViewModelState())
    val uiState = _vmState
        .map(CategoriesViewModelState::asUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = _vmState.value.asUiState()
        )

    fun onAction(action: CategoriesAction) {
        when (action) {
            is CategoriesAction.UpdateSearchKey -> updateSearchQuery(action.query)
            is CategoriesAction.ClickItem -> Unit
            is CategoriesAction.UpdateSearchView -> updateShouldShowSearch(action.shouldShow)
            is CategoriesAction.SaveItem -> Unit
            is CategoriesAction.Navigate -> navigator.to(route = action.route)
        }
    }

    private var searchJob: Job? = null
    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            _vmState.update { state ->
                state.copy(
                    searchState = state.searchState.copy(
                        isLoading = true
                    )
                )
            }
            when (val response = searchBreeds(keyword = query)) {
                is DataResult.Failed -> _vmState.update { state ->
                    state.copy(
                        searchState = state.searchState.copy(
                            isError = response.error,
                            isLoading = false
                        )
                    )
                }

                is DataResult.Success -> _vmState.update { state ->
                    state.copy(
                        searchState = state.searchState.copy(
                            categories = response.data,
                            isLoading = false
                        )
                    )
                }
            }
        }
    }

    private fun updateSearchQuery(query: String) {
        if (query.isEmpty()) return

        _vmState.update { state ->
            state.copy(
                searchState = state.searchState.copy(
                    searchQuery = query
                )
            )
        }
        search(query)
    }

    private fun updateShouldShowSearch(shouldShow: Boolean) {
        _vmState.update { state ->
            state.copy(
                shouldShowSearch = shouldShow
            )
        }
    }
}
