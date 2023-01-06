package com.example.foodcompose.ui.screen.signuplogin.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.foodcompose.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    var dialogState = mutableStateOf(false)


    val userProfile get() = authRepository.getCurrentUser()

    fun signOut(moveTO: () -> Unit) {
        dialogState.value = true
        authRepository.signOut()
        dialogState.value = false
        moveTO()
    }
}