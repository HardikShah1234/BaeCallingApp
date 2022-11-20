package com.harry.baecallingapp.views.home

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.harry.baecallingapp.R
import com.harry.baecallingapp.data.Resource
import com.harry.baecallingapp.navigation.Screens
import com.harry.baecallingapp.utils.OtpView
import com.harry.baecallingapp.utils.PrimaryButton
import com.harry.baecallingapp.utils.VerticalSpacer
import com.harry.baecallingapp.utils.firebase.showMsg
import com.harry.baecallingapp.viewModel.AuthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun PhoneAuthScreen(
    navController: NavController,
    activity: Activity,
    viewModel: AuthViewModel? = hiltViewModel()
) {
    var mobile by remember { mutableStateOf("") }
    var isDailog by remember { mutableStateOf(false) }
    var scope = rememberCoroutineScope()
    var otp by remember { mutableStateOf("")}

    if (isDailog) {
        CircularProgressIndicator()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp), contentAlignment = Alignment.Center){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Enter Mobile Number")
            VerticalSpacer(height = 20.dp)
            OutlinedTextField(
                value = mobile,
                onValueChange = {
                    mobile = it
                },
                label = {
                    Text(text = "+49")
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 55.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                )
            )

            VerticalSpacer(height = 20.dp)

            PrimaryButton(
                label = stringResource(id = R.string.submit),
                modifier = Modifier
                    .height(44.dp)
                    .fillMaxSize()
                    .padding(horizontal = 55.dp)
            ) {
                scope.launch(Dispatchers.Main) {
                    viewModel?.createUserWithPhone(
                        mobile,
                        activity
                    )?.collect {
                        when(it) {
                            is Resource.Loading -> {
                                isDailog = false
                            }
                            is Resource.Failure -> {
                                isDailog = false
                                activity.showMsg(it.exception.message.toString())
                            }
                            is Resource.Success -> {
                                isDailog = true
                                activity.showMsg(it.result)
                            }
                        }
                    }
                }
            }

            VerticalSpacer(height = 20.dp)

            Text(text = "Enter Otp")

            VerticalSpacer(height = 20.dp)

            OtpView(
                otpText = otp,
                onOtpTextChange = {
                    otp = it
                }
            )
            VerticalSpacer(height = 20.dp)
            PrimaryButton(
                label = stringResource(id = com.harry.baecallingapp.R.string.submit),
                modifier = Modifier
                    .height(44.dp)
                    .fillMaxSize()
                    .padding(horizontal = 55.dp)
            ) {
                scope.launch(Dispatchers.Main) {
                    viewModel?.signInWithCredential(
                        otp
                    )?.collect {
                        when(it) {
                            is Resource.Loading -> {
                                isDailog = false
                            }
                            is Resource.Failure -> {
                                isDailog = false
                                activity.showMsg(it.exception.message.toString())
                            }
                            is Resource.Success -> {
                                isDailog = true
                                activity.showMsg(it.result)
                                navController.navigate(Screens.HomeScreen.route) {
                                        popUpTo(Screens.HomeScreen.route) { inclusive = true }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}