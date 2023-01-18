package com.example.foodcompose.ui.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodcompose.R
import com.example.foodcompose.ui.theme.BackGroundLight
import com.example.foodcompose.ui.theme.Black

@Composable
fun TopAppBar() {
    Row(
        modifier = Modifier
            .background(color = BackGroundLight)
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 65.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.snack_bar),
            contentDescription = "Menu Bar",
            tint = Black
        )
        Icon(
            painter = painterResource(id = R.drawable.shopping_cart),
            contentDescription = "Cart",
            tint = Color.Gray
        )
    }
}