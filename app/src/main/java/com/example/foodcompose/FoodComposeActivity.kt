package com.example.foodcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
