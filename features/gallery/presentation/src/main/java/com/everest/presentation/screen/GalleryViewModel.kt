package com.everest.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.everest.domain.usecase.GetGalleries
import com.everest.navigation.Screens
import com.everest.navigation.navigator.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    getGalleries: GetGalleries,
    private val appNavigator: AppNavigator
) : ViewModel() {
    val galleries = getGalleries().cachedIn(viewModelScope)

    fun onAction(action: GalleryAction) {
        when (action) {
            GalleryAction.GoToCategories -> appNavigator.to(Screens.Categories.route)
        }
    }
}
