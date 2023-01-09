package com.example.foodcompose.ui.screen.splash

import androidx.lifecycle.ViewModel
import com.example.foodcompose.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    val isUserAuthenticated get() = authRepository.getCurrentUser() != null
}