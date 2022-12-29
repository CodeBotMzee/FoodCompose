package com.example.foodcompose.ui.screen.signuplogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodcompose.R
import com.example.foodcompose.ui.screen.signuplogin.login.LoginScreen
import com.example.foodcompose.ui.screen.signuplogin.viewmodel.SignUpLoginViewModel
import com.example.foodcompose.ui.theme.Black
import com.example.foodcompose.ui.theme.Primary
import com.example.foodcompose.ui.theme.White


@Composable
fun SignUpLoginScreen(viewModel: SignUpLoginViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        var tabIndex by rememberSaveable { mutableStateOf(0) }
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
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[tabIndex])
                                .padding(horizontal = 20.dp),
                            color = Primary
                        )
                    }, divider = @Composable {
                        Divider()
                    }) {
                    tabsTitle.forEachIndexed { index: Int, title: String ->
                        Tab(
                            selected = tabIndex == index,
                            onClick = { tabIndex = index },
                            modifier = Modifier.padding(horizontal = 20.dp),
                            text = {
                                Text(text = title)
                            })
                    }
                }
            }

        }
        when (tabIndex) {
            0 -> LoginScreen(viewModel = viewModel)
            1 -> {}
        }

    }
}