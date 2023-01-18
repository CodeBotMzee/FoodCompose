package com.example.foodcompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foodcompose.Splash
import com.example.foodcompose.navigateSingleTopTo
import com.example.foodcompose.ui.components.FoodBottomButton
import com.example.foodcompose.ui.components.ProgressDialog
import com.example.foodcompose.ui.screen.home.components.FoodItemCard
import com.example.foodcompose.ui.screen.home.components.TopAppBar
import com.example.foodcompose.ui.theme.BackGroundLight
import com.example.foodcompose.util.Constants.SIGN_OUT

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    //STATES
    val dialogState by remember {
        viewModel.dialogState
    }
    Scaffold(topBar = { TopAppBar() }) {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(it)
        ) {
            val displayName = viewModel.userProfile?.displayName
            if (displayName != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = BackGroundLight),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Hello $displayName",
                    )

                    FoodItemCard()


                }
            }

            FoodBottomButton(onClick = {
                viewModel.signOut {
                    navHostController.navigateSingleTopTo(
                        Splash.route
                    )
                }
            }, text = SIGN_OUT)
            if (dialogState) {
                ProgressDialog {}
            }
        }
    }


}