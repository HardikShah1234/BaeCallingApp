package com.harry.baecallingapp.views.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.harry.baecallingapp.R
import com.harry.baecallingapp.data.Resource
import com.harry.baecallingapp.navigation.Screens
import com.harry.baecallingapp.ui.theme.BaeCallingAppTheme
import com.harry.baecallingapp.ui.theme.CustomBaeCallingAppTheme
import com.harry.baecallingapp.utils.AppHeader
import com.harry.baecallingapp.utils.PrimaryButton
import com.harry.baecallingapp.viewModel.AuthViewModel

@Composable
fun SignupScreen(viewModel: AuthViewModel? = hiltViewModel(), navController: NavController) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val signupFlow = viewModel?.signUpFlow?.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        AppHeader()

        Text(
            text = stringResource(id = R.string.create_account),
            modifier = Modifier.align(CenterHorizontally).padding(top = 15.dp, bottom = 15.dp),
            style = MaterialTheme.typography.h4
        )

        Spacer(modifier = Modifier.size(15.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text(text = stringResource(id = R.string.name))
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = stringResource(id = R.string.email))
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = stringResource(id = R.string.password))
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.size(35.dp))

        PrimaryButton(
            label = stringResource(id = R.string.signup),
            modifier = Modifier
                .height(44.dp)
                .fillMaxSize()
                .padding(horizontal = 55.dp)
        ) {
            viewModel?.signUp(name, email, password)
        }

        signupFlow?.value?.let {
            when (it) {
                is Resource.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(Screens.HomeScreen.route) {
                            popUpTo(Screens.HomeScreen.route) { inclusive = true }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    CustomBaeCallingAppTheme {
        SignupScreen(null,rememberNavController())
    }
}