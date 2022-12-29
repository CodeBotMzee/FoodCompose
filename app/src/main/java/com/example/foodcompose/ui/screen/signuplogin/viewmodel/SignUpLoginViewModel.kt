package com.example.foodcompose.ui.screen.signuplogin.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpLoginViewModel @Inject constructor() : ViewModel() {
    var email = mutableStateOf("")
    var isEmailValid = mutableStateOf(true)
    var password = mutableStateOf("")
    var isPasswordValid = mutableStateOf(true)

    private val testEmail = "test@gmail.com"
    private val testPassword = "test123"

    fun login(): String{
        return when{
            !isEmailValid.value || !isPasswordValid.value -> "Email or Password is not Valid"
            email.value != testEmail -> "Email is Incorrect"
            password.value != testPassword -> "Password is Incorrect"
            else -> "Login Successful"
        }

    }

}