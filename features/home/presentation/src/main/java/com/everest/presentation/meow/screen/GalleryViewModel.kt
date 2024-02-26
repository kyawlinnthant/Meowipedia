package com.everest.presentation.meow.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.everest.domain.usecase.GetMeows
import com.everest.navigation.Screens
import com.everest.navigation.navigator.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    getMeows: GetMeows,
    private val appNavigator: AppNavigator
) : ViewModel() {
    val galleries = getMeows().cachedIn(viewModelScope)

    fun onAction(action: GalleryAction) {
        when (action) {
            GalleryAction.GoToCategories -> appNavigator.to(Screens.Categories.route)
            GalleryAction.GoToUpload -> appNavigator.to(Screens.Upload.route)
        }
    }
}
