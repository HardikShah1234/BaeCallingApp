package com.harry.baecallingapp.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harry.baecallingapp.data.DataStoreRepository
import com.harry.baecallingapp.navigation.Screens
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {
    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screens.SplashScreen.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            repository.readOnBoardingState().collect { completed ->
                if (completed) {
                    _startDestination.value = Screens.PhoneAuthScreen.route
                } else {
                    _startDestination.value = Screens.WelcomeScreen.route
                }
            }
            _isLoading.value = false
        }
    }
}