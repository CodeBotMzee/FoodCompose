package com.example.foodcompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foodcompose.Splash
import com.example.foodcompose.navigateSingleTopTo
import com.example.foodcompose.ui.components.FoodBottomButton
import com.example.foodcompose.ui.components.ProgressDialog
import com.example.foodcompose.ui.screen.signuplogin.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    //STATES
    val dialogState by remember {
        viewModel.dialogState
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        val displayName = viewModel.userProfile?.displayName
        if (displayName != null) {
            Text(text = "Hello $displayName", modifier = Modifier.align(Alignment.Center))
        }
        FoodBottomButton(onClick = {
            viewModel.signOut {
                navHostController.navigateSingleTopTo(
                    Splash.route
                )
            }
        }, text = "Sign Out")
        if (dialogState) {
            ProgressDialog {}
        }
    }

}