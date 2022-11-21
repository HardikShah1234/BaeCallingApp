package com.harry.baecallingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.harry.baecallingapp.navigation.setUpNavController
import com.harry.baecallingapp.ui.theme.CustomBaeCallingAppTheme
import com.harry.baecallingapp.viewModel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().let {
            it.setKeepOnScreenCondition {
                splashViewModel.isLoading.value.not()
            }
            setContent {
                CustomBaeCallingAppTheme {
                    val screen by splashViewModel.startDestination
                    setUpNavController(
                        controller = rememberNavController(),
                        startDestination = screen
                    )
                }
            }
        }
    }
}