package com.harry.baecallingapp.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.harry.baecallingapp.R
import com.harry.baecallingapp.ui.theme.BaeCallingAppTheme
import com.harry.baecallingapp.ui.theme.spacing
import com.harry.baecallingapp.views.home.LoginScreen

@Composable
fun AppHeader() {
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val spacing = MaterialTheme.spacing

        Image(
            modifier = Modifier
                .size(128.dp, 128.dp),
            painter = painterResource(id = R.drawable.ic_chat_bubble_icon),
            contentDescription = stringResource(id = R.string.app_name)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = spacing.large),
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppHeaderPreview() {
    BaeCallingAppTheme {
        AppHeader()
    }
}