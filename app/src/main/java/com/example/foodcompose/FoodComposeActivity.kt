package com.example.foodcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.foodcompose.ui.theme.FoodComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodComposeTheme {
                val navController = rememberNavController()
                FoodComposeNavHost(navHostController = navController)
            }
        }
    }
}
