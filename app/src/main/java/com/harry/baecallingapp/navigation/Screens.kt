package com.harry.baecallingapp.navigation

sealed class Screens(val route : String) {

    object SplashScreen : Screens("splash")
    object WelcomeScreen : Screens("welcome")
    object LoginScreen : Screens("Login Screen")
    object HomeScreen : Screens("Home Screen")
    object SignupScreen : Screens("signup")
    object PhoneAuthScreen : Screens("phone")
}