package com.harry.baecallingapp.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.harry.baecallingapp.R

val regularBaeFont = FontFamily(
    listOf(
        Font(R.font.lato_regular, FontWeight.Medium),
        Font(R.font.lato_italic, FontWeight.Medium, FontStyle.Italic),
        Font(R.font.lato_bolditalic, FontWeight.Bold, FontStyle.Italic),
        Font(R.font.lato_bold, FontWeight.Bold)
    )
)