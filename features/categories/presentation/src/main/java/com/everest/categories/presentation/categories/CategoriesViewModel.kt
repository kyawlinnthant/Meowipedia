package com.everest.categories.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.categories.domain.usecase.FetchCategories
import com.everest.categories.domain.usecase.SearchCategories
import com.everest.categories.domain.vo.CategoryVO
import com.everest.categories.presentation.categories.state.CategoriesViewModelState
import com.everest.navigation.navigator.AppNavigator
import com.everest.util.result.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val fetchCategories: FetchCategories,
    private val searchCategories: SearchCategories,
    private val navigator: AppNavigator,
) : ViewModel() {
    private val vmState = MutableStateFlow(CategoriesViewModelState())
    val uiState = vmState
        .map(CategoriesViewModelState::asUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asUiState()
        )

    fun fetch() {
        viewModelScope.launch {
            vmState.update { state ->
                state.copy(
                    listState = state.listState.copy(
                        isLoading = true
                    )
                )
            }
            when (val response = fetchCategories()) {
                is DataResult.Failed -> vmState.update { state ->
                    state.copy(
                        listState = state.listState.copy(
                            isError = response.error,
                            isLoading = false
                        )
                    )
                }

                is DataResult.Success -> vmState.update { state ->
                    state.copy(
                        listState = state.listState.copy(
                            categories = response.data,
                            isLoading = false
                        )
                    )
                }
            }
        }
    }

    fun onAction(action: CategoriesAction) {
        when (action) {
            is CategoriesAction.UpdateSearchKey -> updateSearchQuery(action.query)
            is CategoriesAction.ClickItem -> operateItemClick(action.item)
            is CategoriesAction.UpdateSearchView -> updateShouldShowSearch(action.shouldShow)
            is CategoriesAction.Navigate -> navigator.to(route = action.route)
        }
    }

    private var searchJob: Job? = null
    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            vmState.update { state ->
                state.copy(
                    searchState = state.searchState.copy(
                        isLoading = true
                    )
                )
            }
            when (val response = searchCategories(keyword = query)) {
                is DataResult.Failed -> vmState.update { state ->
                    state.copy(
                        searchState = state.searchState.copy(
                            isError = response.error,
                            isLoading = false
                        )
                    )
                }

                is DataResult.Success -> vmState.update { state ->
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
        vmState.update { state ->
            state.copy(
                searchState = state.searchState.copy(
                    searchQuery = query
                )
            )
        }
        if (query.isEmpty()) return
        search(query)
    }

    private fun updateShouldShowSearch(shouldShow: Boolean) {
        vmState.update { state ->
            state.copy(
                shouldShowSearch = shouldShow
            )
        }
    }

    private fun operateItemClick(category: CategoryVO) {
    }
}
