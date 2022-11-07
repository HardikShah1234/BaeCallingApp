package com.harry.baecallingapp.utils

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harry.baecallingapp.ui.theme.BaeCallingAppTheme
import com.harry.baecallingapp.ui.theme.CustomTheme

@Composable
fun PrimaryButton(
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    rippleColor: Color = CustomTheme.colors.ripple,
    backgroundColor: Color = CustomTheme.colors.brand,
    textStyleEnabled: TextStyle = CustomTheme.typography.body1,
    onClick: () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides BasePrimaryButtonRippleTheme(rippleColor)) {
        Button(
            modifier = modifier.height(44.dp),
            enabled = enabled,
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                hoveredElevation = 0.dp,
                focusedElevation = 0.dp
            ),
            contentPadding = PaddingValues(
                horizontal = 14.dp,
                vertical = 14.dp
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                disabledBackgroundColor = CustomTheme.colors.customWhite
            ),
            onClick = onClick
        ) {
            Text(
                text = label.uppercase(),
                style = if (enabled) {
                    textStyleEnabled
                } else {
                    CustomTheme.typography.body1
                }
            )
        }
    }
}

private data class BasePrimaryButtonRippleTheme(val rippleColor: Color): RippleTheme {

    @Composable
    override fun defaultColor() = rippleColor

    @Composable
    override fun rippleAlpha() = RippleAlpha(0.26f, 0.26f,0.26f,0.26f)
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ButtonLightPreview() {
    Column {
        BaeCallingAppTheme {
            PrimaryButton(
                enabled = true,
                label = "Test"
            ) {}
        }
    }
}