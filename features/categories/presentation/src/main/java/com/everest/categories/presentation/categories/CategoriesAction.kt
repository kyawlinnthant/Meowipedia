package com.everest.categories.presentation.categories

import com.everest.categories.domain.vo.CategoryVO

sealed interface CategoriesAction {
    data class UpdateSearchKey(val query: String) : CategoriesAction
    data class UpdateSearchView(val shouldShow: Boolean) : CategoriesAction
    data class ClickItem(val item: CategoryVO) : CategoriesAction
    data class SaveItem(val item: CategoryVO) : CategoriesAction
    data class Navigate(val route: String) : CategoriesAction
}
