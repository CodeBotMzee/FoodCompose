package com.example.foodcompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FoodBottomButton(onClick: () -> Unit, colors: ButtonColors = ButtonDefaults.buttonColors()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp, vertical = 35.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            elevation = ButtonDefaults.elevation(3.dp),
            colors = colors,
            contentPadding = PaddingValues(vertical = 25.dp)
        ) {
            Text(text = "Get Started", style = MaterialTheme.typography.button)
        }
    }
}