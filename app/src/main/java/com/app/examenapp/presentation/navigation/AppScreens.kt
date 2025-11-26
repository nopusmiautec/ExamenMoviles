package com.app.examenapp.presentation.navigation

sealed class AppScreens(
    val route: String,
) {
    object Welcome : AppScreens("welcome_screen")

    object Home : AppScreens("home_screen")

    object Characters : AppScreens("characters_screen")
}
