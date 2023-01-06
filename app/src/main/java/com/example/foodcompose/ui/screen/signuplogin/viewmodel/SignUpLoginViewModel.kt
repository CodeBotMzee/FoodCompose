package com.example.foodcompose.ui.screen.signuplogin.viewmodel


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.foodcompose.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.userProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpLoginViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    var pagerState = mutableStateOf(0)

    //Login States
    var email = mutableStateOf("")
    var emailValid = mutableStateOf(true)
    var password = mutableStateOf("")
    var passwordValid = mutableStateOf(true)

    //Sign Up States
    var firstName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var signUpEmail = mutableStateOf("")
    var signUpPassword = mutableStateOf("")
    var confirmPassword = mutableStateOf("")

    var dialogState = mutableStateOf(false)

    var user = authRepository.getCurrentUser()

    fun newUserEmail() {
        signUpEmail.value = email.value
        email.value = ""
        password.value = ""
    }

    fun login(moveTO: () -> Unit, context: Context, moveTODialogBuilder: () -> Unit) {
        dialogState.value = true
        authRepository.signInWithEmailAndPassword(email = email.value, password = password.value)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    dialogState.value = false
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    moveTO()
                    resetStates()
                } else {

                    val errorCode = (it.exception as FirebaseAuthException).errorCode
                    Log.e("Exception ", errorCode)
                    dialogState.value = false
                    when (errorCode) {
                        "ERROR_USER_NOT_FOUND" -> {
                            Toast.makeText(context, "User Does Not Exist", Toast.LENGTH_SHORT)
                                .show()
                            moveTODialogBuilder()
                        }
                    }
                }
            }


    }

    fun signUp(moveTO: () -> Unit, context: Context) {
        dialogState.value = true
        authRepository.signUpWithEmailAndPassword(
            email = signUpEmail.value,
            password = signUpPassword.value
        ).addOnCompleteListener { it ->
            if (it.isSuccessful) {
                authRepository.changeUserProfile(profileChangeRequest = userProfileChangeRequest {
                    displayName = firstName.value + " " + lastName.value
                })
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                            moveTO()
                            dialogState.value = false
                            resetStates()
                        }
                    }

            } else {
                Toast.makeText(context, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                dialogState.value = false
            }
        }
    }

    private fun resetStates() {
        // Login States
        email.value = ""
        password.value = ""
        // Sign Up States
        firstName.value = ""
        lastName.value = ""
        signUpEmail.value = ""
        signUpPassword.value = ""
        confirmPassword.value = ""
    }

}