package com.example.foodcompose.ui.screen.signuplogin.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpLoginViewModel @Inject constructor() : ViewModel() {
    //Login States
    var email = mutableStateOf("")
    var emailValid = mutableStateOf(true)
    var password = mutableStateOf("")
    var passwordValid = mutableStateOf(true)

    //Sign Up States
    var firstName =  mutableStateOf("")
    var lastName =  mutableStateOf("")
    var signUpEmail =  mutableStateOf("")
    var signUpPassword =  mutableStateOf("")
    var confirmPassword =  mutableStateOf("")

    private val testEmail = "test@gmail.com"
    private val testPassword = "test123"

    fun login(): String{
        return when{
            !emailValid.value || !passwordValid.value -> "Email or Password is not Valid"
            email.value != testEmail -> "Email is Incorrect"
            password.value != testPassword -> "Password is Incorrect"
            else -> "Login Successful"
        }

    }

}