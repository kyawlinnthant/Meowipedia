package com.everest.presentation.meow.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.everest.domain.usecase.GetMeows
import com.everest.navigation.navigator.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MeowsViewModel @Inject constructor(
    getMeows: GetMeows,
    private val appNavigator: AppNavigator
) : ViewModel() {
    val galleries = getMeows().cachedIn(viewModelScope)

    fun onAction(action: MeowsAction) {
        when (action) {
            is MeowsAction.Navigate -> appNavigator.to(action.route)
            MeowsAction.Upload -> {
                // todo : check logged in or not
            }
        }
    }
}
