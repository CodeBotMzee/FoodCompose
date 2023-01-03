package com.example.foodcompose.data.repository

import com.example.foodcompose.data.authentication.FirebaseSource
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(private val firebaseSource: FirebaseSource) {

    fun signUpWithEmailAndPassword(email: String, password: String) =
        firebaseSource.signUpWithEmailAndPassword(email, password)

    fun signInWithEmailAndPassword(email: String, password: String) =
        firebaseSource.signInWithEmailAndPassword(email, password)

    fun getCurrentUser() = firebaseSource.getCurrentUser()

    //Send Email Verification
    fun sendEmailVerification() = getCurrentUser()?.sendEmailVerification()

    //Update User Profile Data
    fun changeUserProfile(profileChangeRequest: UserProfileChangeRequest) =
        getCurrentUser()?.updateProfile(profileChangeRequest)

    fun signOut() = firebaseSource.signOut()
}