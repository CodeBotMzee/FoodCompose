package com.example.foodcompose.ui.screen.splash

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foodcompose.Home
import com.example.foodcompose.R
import com.example.foodcompose.SignUpLogin
import com.example.foodcompose.navigateSingleTopTo
import com.example.foodcompose.ui.components.FoodBottomButton
import com.example.foodcompose.ui.theme.Primary
import com.example.foodcompose.ui.theme.SplashBackGround
import com.example.foodcompose.ui.theme.White
import com.example.foodcompose.util.Constants.GET_STARTED


@Composable
fun SplashScreen(
    navHostController: NavHostController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SplashBackGround)
    ) {

        Log.d("IS USER LOGGED IN", viewModel.isUserAuthenticated.toString())
        Image(
            painter = painterResource(id = R.drawable.splash_background),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(modifier = Modifier.padding(start = 50.dp, top = 55.dp)) {
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "App Icon",
                modifier = Modifier
                    .height(75.dp)
                    .width(75.dp)
            )
            Text(text = "Food for Everyone", style = MaterialTheme.typography.h1)
        }
        FoodBottomButton(
            onClick = {
                if (viewModel.isUserAuthenticated) {
                    navHostController.navigateSingleTopTo(Home.route)
                } else {
                    navHostController.navigateSingleTopTo(SignUpLogin.route)
                }
            },
            text = GET_STARTED,
            colors = ButtonDefaults.buttonColors(backgroundColor = White, contentColor = Primary)
        )

    }


}