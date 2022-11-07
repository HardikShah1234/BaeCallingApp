package com.harry.baecallingapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.harry.baecallingapp.utils.Logo
import com.harry.baecallingapp.views.SplashScreen
import com.harry.baecallingapp.views.home.HomeScreen
import com.harry.baecallingapp.views.home.LoginScreen
import com.harry.baecallingapp.views.onboarding.WelcomeScreen

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun setUpNavController(
    controller: NavHostController,
    startDestination: String
) {

    NavHost(
        navController = controller,
        startDestination = startDestination
    ) {

        composable(Screens.SplashScreen.route) {
            SplashScreen(navController = controller)
        }

        composable(Screens.WelcomeScreen.route) {
            /**
             * Show Welcome Screen
             */
            WelcomeScreen(navController = controller)
        }

        composable(Screens.LoginScreen.route) {
            /**
             * Show Login Screen
             */
            LoginScreen(navController = controller)
        }

        composable(Screens.HomeScreen.route) {
            HomeScreen(navController = controller)
        }
    }
}