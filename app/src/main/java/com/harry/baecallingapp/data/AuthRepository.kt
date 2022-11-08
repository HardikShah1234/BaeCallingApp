package com.harry.baecallingapp.data

import com.google.firebase.auth.FirebaseUser

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
}