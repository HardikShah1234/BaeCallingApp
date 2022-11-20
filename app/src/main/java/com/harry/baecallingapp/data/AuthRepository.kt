package com.harry.baecallingapp.data

import android.app.Activity
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow


interface AuthRepository {
    /**
     * When user is already loggedIn we can get information.
     */
    val currentUser: FirebaseUser?

    /**
     * Login the user.
     */
    suspend fun login(email: String, password: String): Resource<FirebaseUser>

    /**
     * Successfully signed up the user.
     */
    suspend fun signUp(name: String, email: String, password: String): Resource<FirebaseUser>

    /**
     * Logout from the app.
     */
    fun logout()

    /**
     * Successfully create User with phone.
     */
    fun createUserWithPhone(phone: String, activity: Activity) : Flow<Resource<String>>

    /**
     * Successfully signin with credentials (phone).
     */
    fun signInWithPhone(otp: String) : Flow<Resource<String>>
}