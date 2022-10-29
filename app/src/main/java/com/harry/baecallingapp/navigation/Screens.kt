package com.harry.baecallingapp.navigation

sealed class Screens(val route : String) {

    object SplashScreen : Screens("splash")
    object LoginScreen : Screens("Login Screen")
}