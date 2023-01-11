package com.example.foodcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.foodcompose.ui.theme.FoodComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FoodComposeTheme {
                val viewModel: ApplicationViewModel = hiltViewModel()
                val isUserAuthenticated by remember {
                    mutableStateOf(viewModel.isUserAuthenticated)
                }
                val startDestination: String = if(isUserAuthenticated){
                    Home.route
                }else{
                    Splash.route
                }
                val navController = rememberNavController()
                FoodComposeNavHost(navHostController = navController, startDestination = startDestination)
            }
        }
    }
}
