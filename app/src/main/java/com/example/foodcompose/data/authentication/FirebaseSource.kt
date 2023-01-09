package com.example.foodcompose.data.authentication


import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FirebaseSource @Inject constructor(private val firebaseAuth: FirebaseAuth) {
    //Sign Up with Email and Password
    fun signUpWithEmailAndPassword(email: String, password: String) =
        firebaseAuth.createUserWithEmailAndPassword(email, password)

    //Sign In with Email and Password
    fun signInWithEmailAndPassword(email: String, password: String) =
        firebaseAuth.signInWithEmailAndPassword(email, password)

    //Get Current Logged In User
    fun getCurrentUser() = firebaseAuth.currentUser

    //Sign Out
    fun signOut() = firebaseAuth.signOut()

    fun signInUsingGoogleCredentials(GoogleCredential: AuthCredential) =
        firebaseAuth.signInWithCredential(GoogleCredential)
}