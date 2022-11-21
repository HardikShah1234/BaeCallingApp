package com.harry.baecallingapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.harry.baecallingapp.BuildConfig

@Composable
fun BaseTheme(
    colors: BaeColors = CustomTheme.colors,
    typography: Typography = CustomTheme.typography,
    content: @Composable () -> Unit
) {
    val rememberedColors = remember {
        colors
    }
    val rememberedTypography = remember {
        typography
    }

    DebugMaterialTheme {
        CompositionLocalProvider(
            LocalColors provides rememberedColors,
            LocalTypography provides rememberedTypography,
            content = content
        )
    }

}

@Composable
fun CustomBaeCallingAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = baeColorsTheme(darkTheme)
    val typography = Typography

    BaseTheme(colors, typography) {
        CompositionLocalProvider {
            content()
        }
    }
}

@Composable
private fun baeColorsTheme(darkTheme: Boolean = isSystemInDarkTheme()): BaeColors {
    return if (darkTheme) {
        baeColorsCallDark
    } else {
        baeColorsCallLight
    }
}

@Composable
private fun DebugMaterialTheme(content: @Composable () -> Unit) {
    val colors = if (BuildConfig.DEBUG) {
        Colors(
            primary = undefined,
            primaryVariant = undefined,
            secondary = undefined,
            secondaryVariant = undefined,
            background = undefined,
            surface = undefined,
            error = undefined,
            onPrimary = undefined,
            onSecondary = undefined,
            onBackground = undefined,
            onSurface = undefined,
            onError = undefined,
            isLight = true
        )
    } else {
        MaterialTheme.colors
    }
    MaterialTheme(colors, Typography(defaultFontFamily = regularBaeFont), content = content)
}

private val undefined = if (BuildConfig.DEBUG) {
    Color.DarkGray
} else {
    Color.Transparent
}

object CustomTheme {

    val colors: BaeColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

private val LocalColors = staticCompositionLocalOf { baeColorsUninitialized }
private val LocalTypography = staticCompositionLocalOf { Typography }