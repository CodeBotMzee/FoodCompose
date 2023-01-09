package com.example.foodcompose.data.repository

import com.example.foodcompose.data.authentication.FirebaseSource
import com.example.foodcompose.util.Constants.SIGN_IN_REQUEST
import com.example.foodcompose.util.Constants.SIGN_UP_REQUEST
import com.example.foodcompose.util.Resource
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class AuthRepository @Inject constructor(
    private val firebaseSource: FirebaseSource,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
) {

    fun getCurrentUser() = firebaseSource.getCurrentUser()

    fun signUpWithEmailAndPassword(email: String, password: String) =
        firebaseSource.signUpWithEmailAndPassword(email, password)

    fun signInWithEmailAndPassword(email: String, password: String) =
        firebaseSource.signInWithEmailAndPassword(email, password)

    suspend fun oneTapSignInWithGoogle(): Resource<BeginSignInResult> {
        return try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            Resource.success(signInResult)
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                Resource.success(signUpResult)
            } catch (e: Exception) {
                val errorMessage = e.message
                if (!errorMessage.isNullOrBlank()) {
                    Resource.error(errorMessage)
                } else {
                    Resource.error("Error Occurred")
                }
            }
        }
    }


    suspend fun signInWithGoogle(googleCredential: AuthCredential): Resource<Boolean> {
        return try {
            firebaseSource.signInUsingGoogleCredentials(googleCredential).await()
            Resource.success(true)
        } catch (e: Exception) {
            val errorMessage = e.message
            if (!errorMessage.isNullOrBlank()) {
                Resource.error(errorMessage)
            } else {
                Resource.error("Error Occurred")
            }
        }
    }

    //Send Email Verification
    fun sendEmailVerification() = getCurrentUser()?.sendEmailVerification()

    //Update User Profile Data
    fun changeUserProfile(profileChangeRequest: UserProfileChangeRequest) =
        getCurrentUser()?.updateProfile(profileChangeRequest)

    fun signOut() = firebaseSource.signOut()
}