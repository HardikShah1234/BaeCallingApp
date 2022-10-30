package com.harry.baecallingapp.ui.theme

import androidx.compose.ui.graphics.Color
import com.harry.baecallingapp.BuildConfig
import javax.annotation.concurrent.Immutable

@Immutable
data class BaeColors(
    val greyscalePrimary: Color,
    val greyscaleSecondary: Color,
    val greyscaleTertiary: Color,
    val darkBlueShade: Color,
    val lightBlueShade: Color,
    val customBaeBackground: Color,
    val customWhite: Color,
    val iconPrimary: Color,
    val iconSecondary: Color,

    val undefined: Color,

    val isLight: Boolean
)

private val undefined = Color.Transparent

internal val baeColorsUninitialized = BaeColors(
    greyscalePrimary = undefined,
    greyscaleSecondary = undefined,
    greyscaleTertiary = undefined,
    darkBlueShade = undefined,
    lightBlueShade = undefined,
    customBaeBackground = undefined,
    customWhite = undefined,
    iconPrimary = undefined,
    iconSecondary = undefined,

    undefined = undefined,

    isLight = false
)

internal val baeColorsLight = baeColorsUninitialized.copy(
    greyscalePrimary = Color(0xFF161515),
    greyscaleSecondary = Color(0xFF787878),
    greyscaleTertiary = Color(0xFFBEBEBE),
    darkBlueShade = Color(0xFF0A1478),
    lightBlueShade = Color(0xFFBEC8FF),
    customBaeBackground = Color(0xFFFFFFFF),
    customWhite = Color(0xFFFFFFFF),
    iconPrimary = Color(0xFF161515),
    iconSecondary = Color(0xFF787878),

    isLight = true
)

internal val baeColorsDark = baeColorsUninitialized.copy(
    greyscalePrimary = Color(0xFFF5F5F5),
    greyscaleSecondary = Color(0xFFB1B1B1),
    greyscaleTertiary = Color(0xFFB1B1B1),
    customBaeBackground = Color(0xFF161515),
    darkBlueShade = Color(0xFF0A1478),
    lightBlueShade = Color(0xFFBEC8FF),
    customWhite = Color(0xFFF5F5F5),
    iconPrimary = Color(0xFFF5F5F5),
    iconSecondary = Color(0xFFB1B1B1),

    isLight = false
)