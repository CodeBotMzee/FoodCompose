package com.example.foodcompose.ui.screen.signuplogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foodcompose.Login
import com.example.foodcompose.R
import com.example.foodcompose.navigateSingleTopTo
import com.example.foodcompose.ui.screen.signuplogin.login.LoginScreen
import com.example.foodcompose.ui.theme.Black
import com.example.foodcompose.ui.theme.Primary
import com.example.foodcompose.ui.theme.White


@Composable
fun SignUpLoginScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        var tabIndex by remember { mutableStateOf(0) }
        val tabsTitle = listOf("Login", "Sign Up")
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp), backgroundColor = White
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_large),
                contentDescription = "Large Icon",
                modifier = Modifier.padding(all = 100.dp),
                alignment = Alignment.Center
            )
            Column(verticalArrangement = Arrangement.Bottom) {

                TabRow(selectedTabIndex = tabIndex,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 50.dp),
                    backgroundColor = White,
                    contentColor = Black,
                    indicator = @Composable { tabPositions: List<TabPosition> ->
                        TabRowDefaults.Indicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                            color = Primary
                        )
                    }) {
                    tabsTitle.forEachIndexed { index: Int, title: String ->
                        Tab(selected = tabIndex == index, onClick = { tabIndex = index }, text = {
                            Text(text = title)
                        })
                    }
                }
            }

        }
        when (tabIndex) {
            0 -> LoginScreen()
            1 -> {}
        }

    }
}