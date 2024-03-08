package com.everest.data.paging

sealed interface PageState {
    data class Refresh(val page: Int) : PageState
    data class Prepend(val page: Int?) : PageState
    data class Append(val page: Int?) : PageState
}
