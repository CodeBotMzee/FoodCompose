package com.example.foodcompose.ui.screen

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodcompose.R
import com.example.foodcompose.ui.components.FoodBottomButton
import com.example.foodcompose.ui.theme.Primary
import com.example.foodcompose.ui.theme.SplashBackGround
import com.example.foodcompose.ui.theme.White



@Composable
fun SplashScreen(onClickGetStarted: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SplashBackGround)
    ) {
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
            onClick = onClickGetStarted,
            colors = ButtonDefaults.buttonColors(backgroundColor = White, contentColor = Primary)
        )

    }


}