package com.harry.baecallingapp.viewModel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.harry.baecallingapp.data.AuthRepository
import com.harry.baecallingapp.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signUpFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signUpFlow: StateFlow<Resource<FirebaseUser>?> = _signUpFlow

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if (repository.currentUser != null) {
            _loginFlow.update { Resource.Success(repository.currentUser!!) }
        }
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginFlow.update { Resource.Loading }
        val result = repository.login(email, password)
        _loginFlow.update { result }
    }

    fun signUp(name: String, email: String, password: String) = viewModelScope.launch {
        _signUpFlow.update { Resource.Loading }
        val result = repository.signUp(name, email, password)
        _signUpFlow.update { result }
    }

    fun createUserWithPhone(phone: String, activity: Activity) =
        repository.createUserWithPhone(phone, activity)

    fun signInWithCredential(code:String) =
        repository.signInWithPhone(otp = code)


    fun logout() {
        repository.logout()
        _loginFlow.update { null }
        _signUpFlow.update { null }
    }
}