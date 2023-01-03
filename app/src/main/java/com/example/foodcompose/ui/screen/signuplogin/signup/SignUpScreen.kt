package com.example.foodcompose.ui.screen.signuplogin.signup

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.foodcompose.ui.components.FoodBottomButton
import com.example.foodcompose.ui.screen.signuplogin.viewmodel.SignUpLoginViewModel
import com.example.foodcompose.ui.theme.Primary

@Composable
fun SignUpScreen(viewModel: SignUpLoginViewModel, textFieldColors: TextFieldColors) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        //Fields States
        var firstName by rememberSaveable { viewModel.firstName }
        var lastName by rememberSaveable { viewModel.lastName }
        var email by rememberSaveable { viewModel.signUpEmail }
        var password by rememberSaveable { viewModel.signUpPassword }
        var confirmPassword by rememberSaveable { viewModel.confirmPassword }

        //Fields Validity States
        var isEmailValid by rememberSaveable { mutableStateOf(true) }
        var isPasswordValid by rememberSaveable { mutableStateOf(true) }
        var isConfirmPasswordValid by rememberSaveable { mutableStateOf(true) }

        //Are Fields Empty
        var isFirstNameFieldEmpty by remember { mutableStateOf(false) }
        var isLastNameFieldEmpty by remember { mutableStateOf(false) }
        var isEmailFieldEmpty by remember { mutableStateOf(false) }
        var isPasswordFieldEmpty by remember { mutableStateOf(false) }
        var isConfirmPasswordFieldEmpty by remember { mutableStateOf(false) }

        //Focus Manager
        val focusManager = LocalFocusManager.current

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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .wrapContentHeight()
                ) {

                    //First Name Field
                    TextField(
                        value = firstName,
                        onValueChange = {
                            firstName = it
                            if (firstName.isNotEmpty()) {
                                isFirstNameFieldEmpty = false
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
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
                    if (isFirstNameFieldEmpty) {
                        Text(
                            "Please Enter your First Name",
                            color = Primary,
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                }

                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(20.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .wrapContentHeight()
                ) {

                    //Last Name Field
                    TextField(
                        value = lastName,
                        onValueChange = {
                            lastName = it
                            if (lastName.isNotEmpty()) {
                                isLastNameFieldEmpty = false
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
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
                    if (isLastNameFieldEmpty) {
                        Text(
                            "Please Enter your Last Name",
                            color = Primary,
                            style = MaterialTheme.typography.subtitle2
                        )
                    }

                }
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
            )

            //Email Field
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

            //Password Field
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
                    imeAction = ImeAction.Next
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
                    .height(45.dp)
            )

            //Confirm Password Field
            TextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    if (password.isEmpty()) {
                        isConfirmPasswordValid = true
                    } else {
                        isConfirmPasswordFieldEmpty = false
                        isConfirmPasswordValid = password == confirmPassword
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = {
                    Text(
                        text = "Confirm Password"
                    )
                },
                placeholder = { Text(text = "*********") },
                isError = !isConfirmPasswordValid,
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
            if (isConfirmPasswordFieldEmpty) {
                Text(
                    "Please Confirm Password",
                    color = Primary,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            if (!isConfirmPasswordValid) {
                Text(
                    "Password Doesn't Match",
                    color = Primary,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
            )

        }

        //SignUp Button
        FoodBottomButton(onClick = {
            isFirstNameFieldEmpty = firstName.isEmpty()
            isLastNameFieldEmpty = lastName.isEmpty()
            isEmailFieldEmpty = email.isEmpty()
            isPasswordFieldEmpty = password.isEmpty()
            isConfirmPasswordFieldEmpty = confirmPassword.isEmpty()
        }, text = "Sign Up")

    }

}

