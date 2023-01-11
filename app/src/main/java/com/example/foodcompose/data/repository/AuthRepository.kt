package com.example.foodcompose.data.repository

import com.example.foodcompose.data.authentication.FirebaseSource
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val firebaseSource: FirebaseSource,
    private var oneTapClient: SignInClient,
    private var signInRequest: BeginSignInRequest,
) {

    fun getCurrentUser() = firebaseSource.getCurrentUser()

    fun signUpWithEmailAndPassword(email: String, password: String) =
        firebaseSource.signUpWithEmailAndPassword(email, password)

    fun signInWithEmailAndPassword(email: String, password: String) =
        firebaseSource.signInWithEmailAndPassword(email, password)

    fun oneTapSignInWithGoogle() =
        oneTapClient.beginSignIn(signInRequest)


    fun signInWithGoogle(googleCredential: AuthCredential) =
        firebaseSource.signInUsingGoogleCredentials(googleCredential)


    //Send Email Verification
    fun sendEmailVerification() = getCurrentUser()?.sendEmailVerification()

    //reset Password Email
    fun sendEmailForPasswordChange(email: String) = firebaseSource.forgotPasswordEmail(email)

    //Confirm Password Reset
    fun confirmPasswordChange(code: String, newPassword: String) =
        firebaseSource.forgotPasswordConfirm(code, newPassword)

    //Update User Profile Data
    fun changeUserProfile(profileChangeRequest: UserProfileChangeRequest) =
        getCurrentUser()?.updateProfile(profileChangeRequest)

    fun signOut() {
        firebaseSource.signOut()
        oneTapClient.signOut()
    }

}