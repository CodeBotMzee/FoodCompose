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
import androidx.navigation.NavHostController
import com.example.foodcompose.Home
import com.example.foodcompose.navigateSingleTopTo
import com.example.foodcompose.ui.components.DialogBuilder
import com.example.foodcompose.ui.components.FoodBottomButton
import com.example.foodcompose.ui.components.ProgressDialog
import com.example.foodcompose.ui.screen.signuplogin.viewmodel.SignUpLoginViewModel
import com.example.foodcompose.ui.theme.Primary
import com.example.foodcompose.ui.theme.SFProText

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    viewModel: SignUpLoginViewModel,
    textFieldColors: TextFieldColors
) {
    Column(modifier = Modifier.fillMaxSize()) {

        val context = LocalContext.current

        //Field States
        var email by rememberSaveable { viewModel.email }
        var password by rememberSaveable { viewModel.password }

        //Fields Validity States
        var emailValid by rememberSaveable { viewModel.emailValid }
        var passwordValid by rememberSaveable { viewModel.passwordValid }

        //Are Fields Empty
        var emailFieldEmpty by remember { mutableStateOf(false) }
        var passwordFieldEmpty by remember { mutableStateOf(false) }

        //Progress Dialog State
        var dialogState by remember {
            viewModel.dialogState
        }

        //Dialog Builder
        var dialogBuilder by remember {
            mutableStateOf(false)
        }

        val focusManager = LocalFocusManager.current

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 50.dp, vertical = 65.dp)
        ) {
            //Text Field for Email
            TextField(
                value = email,
                onValueChange = {
                    email = it
                    if (email.isEmpty()) {
                        emailValid = true
                    } else {
                        emailFieldEmpty = false
                        emailValid = Patterns.EMAIL_ADDRESS
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
                isError = !emailValid,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                colors = textFieldColors
            )
            if (emailFieldEmpty) {
                Text(
                    "Please Enter an Email",
                    color = Primary,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            if (!emailValid) {
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
                        passwordValid = true
                    } else {
                        passwordFieldEmpty = false
                        passwordValid = password.length >= 7
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
                isError = !passwordValid,
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
            if (passwordFieldEmpty) {
                Text(
                    "Please Enter a Password",
                    color = Primary,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            if (!passwordValid) {
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
            emailFieldEmpty = email.isEmpty()
            passwordFieldEmpty = password.isEmpty()
            if (emailFieldEmpty || passwordFieldEmpty) {
                Toast.makeText(context, "Fields can't be Empty", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(
                    moveTO = { navHostController.navigateSingleTopTo(Home.route) },
                    context, moveTODialogBuilder = {
                        dialogBuilder = true
                    }
                )
            }
        }, text = "Login")

        if (dialogState) {
            ProgressDialog {
                dialogState = false
            }
        }

        if (dialogBuilder) {
            DialogBuilder(
                onButtonClick = {
                    viewModel.pagerState.value = 1
                    viewModel.newUserEmail()
                },
                onDismissRequest = {
                    dialogBuilder = false
                },
                title = "Would You Like to Sign Up",
                activeButtonText = "Sign UP",
                dismissButtonText = "Cancel"
            )
        }

    }
}