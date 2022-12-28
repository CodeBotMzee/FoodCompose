package com.example.foodcompose.ui.screen.signuplogin.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.foodcompose.ui.components.FoodBottomButton

@Composable
fun LoginScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        var email by remember { mutableStateOf("") }
        var isEmailValid by remember { mutableStateOf(true) }
        var password by remember { mutableStateOf("") }
        var isPasswordValid by remember { mutableStateOf(true) }



        TextField(value = email, onValueChange = {email = it}, modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), label = { Text(
            text = "Email Address"
        )}, placeholder = { Text(text = "example@gmail.com")}, isError = isEmailValid, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), colors = TextFieldDefaults.textFieldColors()
        )

        FoodBottomButton(onClick = { /*TODO*/ }, text = "Login")
    }
}