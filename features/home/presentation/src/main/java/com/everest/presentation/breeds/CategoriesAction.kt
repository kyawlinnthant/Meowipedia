package com.everest.presentation.breeds

import com.everest.domain.model.categories.breed.BreedVo

sealed interface CategoriesAction {
    data class UpdateSearchKey(val query: String) : CategoriesAction
    data class UpdateSearchView(val shouldShow: Boolean) : CategoriesAction
    data class ClickItem(val item: BreedVo) : CategoriesAction
    data class SaveItem(val item: BreedVo) : CategoriesAction
    data class Navigate(val route: String) : CategoriesAction
}
