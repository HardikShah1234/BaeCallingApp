package com.harry.baecallingapp.views.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.harry.baecallingapp.R
import com.harry.baecallingapp.navigation.Screens
import com.harry.baecallingapp.ui.theme.CustomBaeCallingAppTheme
import com.harry.baecallingapp.ui.theme.CustomTheme
import com.harry.baecallingapp.ui.theme.spacing
import com.harry.baecallingapp.utils.PrimaryButton
import com.harry.baecallingapp.utils.VerticalSpacer
import com.harry.baecallingapp.viewModel.AuthViewModel

@Composable
fun HomeScreen(
    viewModel: AuthViewModel? = hiltViewModel(),
    navController: NavController
) {
    val spacing = MaterialTheme.spacing
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(spacing.medium)
            .padding(top = spacing.extraLarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome_back),
            style = CustomTheme.typography.body1
        )

        Text(
            text = viewModel?.currentUser?.displayName ?: "",
            style = CustomTheme.typography.body1
        )

        Image(
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = stringResource(id = R.string.empty),
            modifier = Modifier.size(128.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(spacing.medium)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = stringResource(id = R.string.name),
                    style = CustomTheme.typography.body1,
                    modifier = Modifier.weight(0.3f),
                )

                Text(
                    text = viewModel?.currentUser?.displayName ?: "",
                    style = CustomTheme.typography.body1,
                    modifier = Modifier.weight(0.7f),
                )
            }

            VerticalSpacer(height = 20.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = stringResource(id = R.string.email),
                    style = CustomTheme.typography.body1,
                    modifier = Modifier.weight(0.3f),
                )
                Text(
                    text = viewModel?.currentUser?.email ?: "",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.weight(0.7f),
                )
            }
            VerticalSpacer(height = 20.dp)

            PrimaryButton(
                label = stringResource(id = R.string.logout),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(44.dp)
                    .fillMaxSize()
                    .padding(horizontal = 55.dp)
            ) {
                viewModel?.logout()
                navController.navigate(Screens.PhoneAuthScreen.route){
                    popUpTo(Screens.HomeScreen.route){ inclusive = true }
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreviewLight() {
    CustomBaeCallingAppTheme {
        HomeScreen(null,rememberNavController())
    }
}