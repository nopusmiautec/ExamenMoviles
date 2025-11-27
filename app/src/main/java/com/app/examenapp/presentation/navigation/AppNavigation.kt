package com.app.examenapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.examenapp.presentation.screens.HomeScreen
import com.app.examenapp.presentation.screens.MenuScreen
import com.app.examenapp.presentation.screens.WelcomeScreen
import com.app.examenapp.presentation.screens.game.GameScreen

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
            HomeScreen(
                onNavigateToCharacters = {
                    navController.navigate(AppScreens.Menu.route)
                },
            )
        }

        composable(AppScreens.Menu.route) {
            MenuScreen(
                onNavigateToGame = { diff, size, isNew ->
                    navController.navigate(AppScreens.Game.createRoute(diff, size, isNew))
                }
            )
        }

        composable(
            route = "game_screen/{difficulty}/{size}/{isNew}",
            arguments = listOf(
                navArgument("difficulty") { type = NavType.StringType },
                navArgument("size") { type = NavType.IntType },
                navArgument("isNew") { type = NavType.BoolType }
            )
        ) { backStackEntry ->
            val difficulty = backStackEntry.arguments?.getString("difficulty") ?: "easy"
            val size = backStackEntry.arguments?.getInt("size") ?: 9
            val isNew = backStackEntry.arguments?.getBoolean("isNew") ?: true

            GameScreen(
                difficulty = difficulty,
                size = size,
                isNewGame = isNew,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
