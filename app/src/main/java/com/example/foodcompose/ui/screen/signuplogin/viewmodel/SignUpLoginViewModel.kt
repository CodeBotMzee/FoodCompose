package com.example.foodcompose.ui.screen.signuplogin.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.foodcompose.data.repository.AuthRepository
import com.example.foodcompose.util.Resource
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpLoginViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
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

    val profileUpdates = userProfileChangeRequest {
        displayName = "${firstName.value} + ${lastName.value}"
    }


    private val testEmail = "test@gmail.com"
    private val testPassword = "test123"

    fun login(): String {
        return when {
            !emailValid.value || !passwordValid.value -> "Email or Password is not Valid"
            email.value != testEmail -> "Email is Incorrect"
            password.value != testPassword -> "Password is Incorrect"
            else -> "Login Successful"
        }

    }

    fun signUp(): MutableStateFlow<Resource<FirebaseUser?>> {
        val result = MutableStateFlow<Resource<FirebaseUser?>>(Resource.loading())


        authRepository.signUpWithEmailAndPassword(
            email = signUpEmail.value,
            password = signUpPassword.value
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                result.value = Resource.success(it.result!!.user)
            }else{
                it.exception.let { exception->
                    result.value = Resource.error(exception?.message.toString(),null)
                }
            }
        }

        return result
    }

}