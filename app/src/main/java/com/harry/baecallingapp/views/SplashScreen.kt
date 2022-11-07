package com.harry.baecallingapp.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.harry.baecallingapp.navigation.Screens
import com.harry.baecallingapp.ui.theme.BaeCallingAppTheme
import com.harry.baecallingapp.utils.Logo
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(key1 = true) {
        delay(3000)
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Logo()
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewSplashScreen() {
    BaeCallingAppTheme {
        SplashScreen(navController = NavController(LocalContext.current))
    }
}