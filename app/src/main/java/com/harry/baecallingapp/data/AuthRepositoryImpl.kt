package com.harry.baecallingapp.data

import android.app.Activity
import android.content.Context
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.harry.baecallingapp.utils.firebase.await
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    @ApplicationContext private val context: Context
) : AuthRepository {

    private lateinit var mVerificationCode: String
    var resentToken: PhoneAuthProvider.ForceResendingToken? = null

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result?.user?.updateProfile(
                UserProfileChangeRequest.Builder().setDisplayName(name).build()
            )?.await()
            Resource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override fun createUserWithPhone(phone: String, activity: Activity): Flow<Resource<String>> =
        callbackFlow {
            trySend(Resource.Loading)

            val onVerificationCallback =
                object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                        TODO("Not yet implemented")
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        trySend(Resource.Failure(p0))
                    }

                    override fun onCodeSent(
                        verificationCode: String,
                        p1: PhoneAuthProvider.ForceResendingToken
                    ) {
                        super.onCodeSent(verificationCode, p1)
                        trySend(Resource.Success("OTP send successfully"))
                        mVerificationCode = verificationCode
                    }

                }

            val options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber("+49$phone")
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(onVerificationCallback)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
            awaitClose { close() }
        }


    override fun signInWithPhone(otp: String): Flow<Resource<String>> = callbackFlow {
        trySend(Resource.Loading)
        val credentials = PhoneAuthProvider.getCredential(mVerificationCode, otp)
        firebaseAuth.signInWithCredential(credentials)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    trySend(Resource.Success("otp verified"))
                }
            }.addOnFailureListener {
                trySend(Resource.Failure(it))
            }
        awaitClose { close() }
    }
}