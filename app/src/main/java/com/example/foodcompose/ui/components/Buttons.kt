package com.example.foodcompose.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FoodBottomButton(
    onClick: () -> Unit,
    text: String,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    interactionSource: MutableInteractionSource = MutableInteractionSource()
) {
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
            interactionSource = interactionSource,
            elevation = ButtonDefaults.elevation(3.dp),
            colors = colors,
            contentPadding = PaddingValues(vertical = 25.dp)
        ) {
            Text(text = text, style = MaterialTheme.typography.button)
        }
    }
}

@Composable
fun FoodButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    interactionSource: MutableInteractionSource = MutableInteractionSource()
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        interactionSource = interactionSource,
        elevation = ButtonDefaults.elevation(3.dp),
        colors = colors,
        contentPadding = PaddingValues(vertical = 25.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.button)
    }
}