package com.everest.navigation

sealed class Screens(val route: String) {
    companion object {
        const val DETAIL_ID = "id"
        const val EMAIL = "email"
    }

    data object AuthGraph : Screens("authGraph")
    data object SettingGraph : Screens("settingGraph")
    data object HomeGraph : Screens("homeGraph")
    data object Login : Screens("login")
    data object Register : Screens("register")
    data object ForgotPassword : Screens("forgotPassword") {
        fun passEmail(email: String) = this.route + "/$email"
        fun getAbsolutePath() = this.route + "/{$EMAIL}"
    }

    data object Meows : Screens("meows")
    data object Categories : Screens("categories")
    data object Settings : Screens("settings")
    data object Collection : Screens("collection")
    data object Upload : Screens("upload")
    data object Detail : Screens("detail") {
        fun passId(id: String, name: String) = this.route + "/$id/$name"
        fun getAbsolutePath() = this.route + "/{$DETAIL_ID}"
    }
}
