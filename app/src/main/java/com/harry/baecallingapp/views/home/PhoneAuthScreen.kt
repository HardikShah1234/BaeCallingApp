package com.harry.baecallingapp.views.home

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.harry.baecallingapp.R
import com.harry.baecallingapp.data.Resource
import com.harry.baecallingapp.navigation.Screens
import com.harry.baecallingapp.ui.theme.BaeCallingAppTheme
import com.harry.baecallingapp.ui.theme.CustomTheme
import com.harry.baecallingapp.utils.*
import com.harry.baecallingapp.utils.firebase.findActivity
import com.harry.baecallingapp.utils.firebase.showMsg
import com.harry.baecallingapp.viewModel.AuthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PhoneAuthScreen(
    navController: NavController,
    activity: Activity,
    viewModel: AuthViewModel? = hiltViewModel()
) {
    var mobile by rememberSaveable { mutableStateOf("") }
    var isDailog by remember { mutableStateOf(false) }
    var scope = rememberCoroutineScope()
    var otp by remember { mutableStateOf("") }
    val fullPhoneNumber = rememberSaveable { mutableStateOf("") }
    val onlyPhoneNumber = rememberSaveable { mutableStateOf("") }


    if (isDailog) {
        CircularProgressIndicator()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Enter Mobile Number")
            VerticalSpacer(height = 20.dp)

            CustomCountryCodePicker(
                text = mobile,
                onValueChange = {
                    mobile = it
                },
                color = CustomTheme.colors.brandTransparent,
                unfocusedBorderColor = MaterialTheme.colors.primary,
                bottomStyle = false,
                shape = RoundedCornerShape(24.dp)
            )

            VerticalSpacer(height = 20.dp)

            PrimaryButton(
                label = stringResource(id = R.string.submit),
                modifier = Modifier
                    .height(44.dp)
                    .fillMaxSize()
                    .padding(horizontal = 55.dp)
            ) {
                if (isPhoneNumber().not()) {
                    fullPhoneNumber.value = getFullPhoneNumber()
                    scope.launch(Dispatchers.Main) {
                        viewModel?.createUserWithPhone(
                            mobile,
                            activity
                        )?.collect {
                            when (it) {
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
                } else {
                    fullPhoneNumber.value = activity.getString(R.string.error)
                }
            }

            VerticalSpacer(height = 20.dp)

            Text(text = stringResource(id = R.string.enter_otp))

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
                        when (it) {
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

@Preview(showBackground = true)
@Composable
fun PhoneAuthScreenPreview() {
    BaeCallingAppTheme {
        LocalContext.current.findActivity()
            ?.let { PhoneAuthScreen(rememberNavController(), it, null) }
    }
}