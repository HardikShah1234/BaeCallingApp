package com.harry.baecallingapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.harry.baecallingapp.ui.SplashScreen

@Composable
fun setUpNavController(controller: NavHostController) {

    NavHost(navController = controller, startDestination = Screens.SplashScreen.route) {

        composable(Screens.SplashScreen.route) {
            /**
             * Show Home Screen
             */
            SplashScreen(navController = controller)
        }

        composable(Screens.LoginScreen.route) {
            /**
             * Show Home Screen
             */
        }
    }
}