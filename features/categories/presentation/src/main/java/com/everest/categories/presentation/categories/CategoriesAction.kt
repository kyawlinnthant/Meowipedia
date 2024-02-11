package com.everest.categories.presentation.categories

sealed interface CategoriesAction{
    data class ChangeSearchKey(val query : String) : CategoriesAction
}