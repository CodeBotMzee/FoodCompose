package com.example.foodcompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodcompose.ui.screen.SplashScreen
import com.example.foodcompose.ui.screen.signuplogin.SignUpLoginScreen

@Composable
fun FoodComposeNavHost(navHostController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = Splash.route,
        modifier = modifier
    ) {
        composable(route = Splash.route) {
            SplashScreen(onClickGetStarted = {
                navHostController.navigateSingleTopTo(route = SignUpLogin.route)
            })
        }
        composable(route = SignUpLogin.route) {
            SignUpLoginScreen()
        }

    }
}


public fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}
