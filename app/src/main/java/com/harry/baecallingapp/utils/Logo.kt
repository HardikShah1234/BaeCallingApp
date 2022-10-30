package com.harry.baecallingapp.utils

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.harry.baecallingapp.R
import com.harry.baecallingapp.ui.theme.BaeCallingAppTheme

private lateinit var customTypeFace: Typeface

@Composable
fun Logo(modifier: Modifier = Modifier) {

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0A1478),
            Color(0xFFBEC8FF)
        )
    )
    val context = LocalContext.current

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(gradient)
    ) {
        val padding = 20
        val path = Path().apply {
            moveTo(x = padding.toFloat() * 6, y = center.y)
            lineTo(x = size.width - padding * 6, y = center.y)
        }
        customTypeFace = context.resources.getFont(R.font.marckscript_regular)

        /**
         * For drawing the text we dont have the such method called [drawText].
         * To overcome this we can use [nativeCanvas].
         */
        this.drawContext.canvas.nativeCanvas.apply {

            drawTextOnPath(
                "BAE Calling App",
                path.asAndroidPath(),
                0f,
                0f,
                Paint().apply {
                    this.color = android.graphics.Color.WHITE
                    this.textSize = 120f
                    this.textAlign = Paint.Align.CENTER
                    this.typeface = customTypeFace
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogoPreview() {
    BaeCallingAppTheme {
        Logo()
    }
}