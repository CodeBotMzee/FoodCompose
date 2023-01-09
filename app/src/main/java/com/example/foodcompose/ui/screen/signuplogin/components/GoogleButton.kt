package com.example.foodcompose.ui.screen.signuplogin.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.foodcompose.ui.theme.Primary
import com.example.foodcompose.ui.theme.White


@Composable
fun FoodIconButton(
    onClick: () -> Unit,
    text: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String = "",
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = White,
        contentColor = Primary
    ),
    interactionSource: MutableInteractionSource = MutableInteractionSource()
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        interactionSource = interactionSource,
        elevation = ButtonDefaults.elevation(5.dp),
        border = BorderStroke(1.dp, Primary),
        colors = colors,
        contentPadding = PaddingValues(vertical = 15.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = icon,
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(45.dp)
                    .padding(end = 2.dp),
                tint = Color.Unspecified
            )
            Text(
                text = text,
                modifier = Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.button
            )
        }

    }
}