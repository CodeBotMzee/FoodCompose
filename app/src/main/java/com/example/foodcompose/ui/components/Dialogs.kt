package com.example.foodcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.foodcompose.ui.theme.Primary

@Composable
fun ProgressDialog(onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        CircularProgressIndicator()
    }
}


//Dialog with 2 Buttons
@Composable
fun DialogBuilder(
    onButtonClick: () -> Unit = {},
    onDismissRequest: () -> Unit,
    title: String,
    activeButtonText: String,
    dismissButtonText: String
) {

    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(Color.White, shape = RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = title,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 25.dp)
                )
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 15.dp, vertical = 25.dp)
                ) {
                    FoodButton(
                        onClick = onButtonClick,
                        text = activeButtonText,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 5.dp)
                    )
                    FoodButton(
                        onClick = onDismissRequest,
                        text = dismissButtonText,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                            contentColor = Primary
                        )
                    )
                }
            }

        }

    }

}