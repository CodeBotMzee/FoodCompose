package com.example.foodcompose.ui.screen.signuplogin.login

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodcompose.ui.components.FoodBottomButton
import com.example.foodcompose.ui.screen.signuplogin.viewmodel.SignUpLoginViewModel
import com.example.foodcompose.ui.theme.Primary
import com.example.foodcompose.ui.theme.SFProText

@Composable
fun LoginScreen(viewModel: SignUpLoginViewModel, textFieldColors: TextFieldColors) {
    Column(modifier = Modifier.fillMaxSize()) {

        val context = LocalContext.current

        //States
        var email by rememberSaveable { viewModel.email }
        var isEmailValid by rememberSaveable { viewModel.isEmailValid }
        var password by rememberSaveable { viewModel.password }
        var isPasswordValid by rememberSaveable { viewModel.isPasswordValid }
        var isEmailFieldEmpty by remember { mutableStateOf(false) }
        var isPasswordFieldEmpty by remember { mutableStateOf(false) }

        val focusManager = LocalFocusManager.current

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 50.dp, vertical = 65.dp)
        ) {
            TextField(
                value = email,
                onValueChange = {
                    email = it
                    if (email.isEmpty()) {
                        isEmailValid = true
                    } else {
                        isEmailFieldEmpty = false
                        isEmailValid = Patterns.EMAIL_ADDRESS
                            .matcher(email)
                            .matches()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = {
                    Text(
                        text = "Email Address"
                    )
                },
                placeholder = { Text(text = "example@gmail.com") },
                isError = !isEmailValid,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                colors = textFieldColors
            )
            if (isEmailFieldEmpty) {
                Text(
                    "Please Enter an Email",
                    color = Primary,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            if (!isEmailValid) {
                Text(
                    "Please Enter a correct Email",
                    color = Primary,
                    style = MaterialTheme.typography.subtitle2
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
            )

            //Text Field for Password
            TextField(
                value = password,
                onValueChange = {
                    password = it
                    if (password.isEmpty()) {
                        isPasswordValid = true
                    } else {
                        isPasswordFieldEmpty = false
                        isPasswordValid = password.length >= 7
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = {
                    Text(
                        text = "Password"
                    )
                },
                placeholder = { Text(text = "*********") },
                isError = !isPasswordValid,
                visualTransformation = PasswordVisualTransformation('*'),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                singleLine = true,
                colors = textFieldColors
            )
            if (isPasswordFieldEmpty) {
                Text(
                    "Please Enter a Password",
                    color = Primary,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            if (!isPasswordValid) {
                Text(
                    "Password is Incorrect",
                    color = Primary,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
            )
            ClickableText(text = AnnotatedString("Forgot Password?"), style = TextStyle(
                color = Primary,
                fontSize = 17.sp,
                fontWeight = FontWeight.W600,
                fontFamily = SFProText
            ), onClick = {/*TODO*/ })
        }

        //Login Button
        FoodBottomButton(onClick = {
            isEmailFieldEmpty = email.isEmpty()
            isPasswordFieldEmpty = password.isEmpty()
            if (!isEmailFieldEmpty && !isPasswordFieldEmpty) {
                Toast.makeText(context, viewModel.login(), Toast.LENGTH_SHORT).show()
            }
        }, text = "Login")
    }
}