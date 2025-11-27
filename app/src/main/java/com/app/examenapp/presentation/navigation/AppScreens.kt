package com.app.examenapp.presentation.navigation

sealed class AppScreens(
    val route: String,
) {
    object Welcome : AppScreens("welcome_screen")

    object Home : AppScreens("home_screen")

    object Menu : AppScreens("menu_screen")

    object Game : AppScreens("game_screen/{difficulty}/{size}/{isNew}") {
        fun createRoute(difficulty: String, size: Int, isNew: Boolean) = "game_screen/$difficulty/$size/$isNew"
    }
}
