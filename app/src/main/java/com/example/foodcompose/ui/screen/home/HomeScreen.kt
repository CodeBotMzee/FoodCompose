package com.example.foodcompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foodcompose.navigateSingleTopTo
import com.example.foodcompose.ui.components.ProgressDialog
import com.example.foodcompose.ui.screen.home.components.BottomAppBar
import com.example.foodcompose.ui.screen.home.components.TopAppBar
import com.example.foodcompose.ui.theme.BackGroundLight

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    //STATES
    val dialogState by remember {
        viewModel.dialogState
    }
    if (dialogState) {
        ProgressDialog {}
    }
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = BackGroundLight),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TopAppBar()

                    BottomAppBar(onTabSelected = { screen -> navHostController.navigateSingleTopTo(screen.route) })
                }
            }

        }
    }
}
