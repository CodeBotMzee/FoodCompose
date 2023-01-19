package com.example.foodcompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodcompose.ui.screen.home.HomeScreen
import com.example.foodcompose.ui.screen.signuplogin.SignUpLoginScreen
import com.example.foodcompose.ui.screen.splash.SplashScreen

@Composable
fun FoodComposeNavHost(
    navHostController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Splash.route) {
            SplashScreen(navHostController)
        }
        composable(route = SignUpLogin.route) {
            SignUpLoginScreen(navHostController)
        }
        composable(route = Home.route) {
            HomeScreen(navHostController)
        }
        composable(route = Favorite.route) {

        }
        composable(route = Profile.route) {

        }
        composable(route = OrderHistory.route) {

        }


    }
}


fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

fun NavHostController.navigateSingleTopToNoBack(route: String) = this.navigate(route) {
    popUpTo(this@navigateSingleTopToNoBack.graph.findStartDestination().id) {
        saveState = true
        inclusive = false
    }
    launchSingleTop = true
    restoreState = true
}