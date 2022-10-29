package com.harry.baecallingapp.utils

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import com.harry.baecallingapp.ui.theme.BaeCallingAppTheme

@Composable
fun Logo(modifier: Modifier = Modifier) {

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color.Gray,
            Color.DarkGray
        )
    )

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
                    this.textSize = 100f
                    this.textAlign = Paint.Align.CENTER
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