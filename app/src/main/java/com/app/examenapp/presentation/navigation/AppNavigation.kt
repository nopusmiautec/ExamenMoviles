package com.app.examenapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.examenapp.presentation.screens.HomeScreen
import com.app.examenapp.presentation.screens.WelcomeScreen

@Suppress("ktlint:standard:function-naming")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.Welcome.route,
    ) {
        composable(route = AppScreens.Welcome.route) {
            WelcomeScreen(
                onNavigateToHome = {
                    navController.navigate(AppScreens.Home.route) {
                        popUpTo(AppScreens.Welcome.route) { inclusive = true }
                    }
                },
            )
        }

        composable(route = AppScreens.Home.route) {
            HomeScreen()
        }
    }
}
