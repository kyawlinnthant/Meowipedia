package com.everest.meowipedia.view

import com.everest.type.ThemeType

data class MainViewModelState(
    val theme: ThemeType = ThemeType.System,
    val dynamic: Boolean = true
) {
    fun asTheme() = theme
    fun asDynamic() = dynamic
}
