package com.harry.baecallingapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.*

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

    MaterialTheme {
        CompositionLocalProvider (
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
private fun baeColorsTheme(darkTheme: Boolean = isSystemInDarkTheme()) : BaeColors {
    return if (darkTheme) {
        baeColorsDark
    } else {
        baeColorsLight
    }
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