package com.example.foodcompose.ui.screen.signuplogin.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.foodcompose.ui.components.FoodBottomButton
import com.example.foodcompose.ui.screen.signuplogin.viewmodel.SignUpLoginViewModel

@Composable
fun SignUpScreen(viewModel: SignUpLoginViewModel, textFieldColors: TextFieldColors) {
    Column(modifier = Modifier.fillMaxSize()) {
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 50.dp, vertical = 65.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                TextField(
                    value = firstName,
                    onValueChange = {
                        firstName = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .wrapContentHeight(),
                    label = {
                        Text(
                            text = "First Name",
                        )
                    },
                    placeholder = { Text(text = "Tom") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true,
                    colors = textFieldColors
                )
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(20.dp)
                )
                TextField(
                    value = lastName,
                    onValueChange = {
                        lastName = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .wrapContentHeight(),
                    label = {
                        Text(
                            text = "Last Name",
                        )
                    },
                    placeholder = { Text(text = "Jerry") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true,
                    colors = textFieldColors
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
            )

        }

        FoodBottomButton(onClick = { /*TODO*/ }, text = "Sign Up")

    }


}