package com.harry.baecallingapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.harry.baecallingapp.utils.firebase.findActivity
import com.harry.baecallingapp.views.SplashScreen
import com.harry.baecallingapp.views.home.HomeScreen
import com.harry.baecallingapp.views.home.LoginScreen
import com.harry.baecallingapp.views.home.PhoneAuthScreen
import com.harry.baecallingapp.views.home.SignupScreen
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

        composable(Screens.SignupScreen.route) {
            SignupScreen(navController = controller)
        }

        composable(Screens.PhoneAuthScreen.route) {
            LocalContext.current.findActivity()?.let { activity ->
                PhoneAuthScreen(
                    navController = controller,
                    activity = activity
                )
            }
        }
    }
}