package com.example.foodcompose.ui.screen.signuplogin

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foodcompose.Home
import com.example.foodcompose.R
import com.example.foodcompose.Splash
import com.example.foodcompose.navigateSingleTopTo
import com.example.foodcompose.ui.screen.signuplogin.login.LoginScreen
import com.example.foodcompose.ui.screen.signuplogin.signup.SignUpScreen
import com.example.foodcompose.ui.theme.Black
import com.example.foodcompose.ui.theme.Primary
import com.example.foodcompose.ui.theme.White
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider.getCredential


@Composable
fun SignUpLoginScreen(
    navHostController: NavHostController,
    viewModel: SignUpLoginViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val context = LocalContext.current
        var tabIndex by rememberSaveable { viewModel.pagerState }
        val launcher =
            rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    try {
                        val credentials =
                            viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                        val googleIdToken = credentials.googleIdToken
                        val googleCredentials = getCredential(googleIdToken, null)
                        viewModel.signInWithGoogle(googleCredentials, moveTO = {
                            navHostController.navigateSingleTopTo(
                                Home.route
                            )
                        }, context)
                    } catch (it: ApiException) {
                        print(it)
                    }
                }
            }

        fun launch(signInResult: BeginSignInResult) {
            val intent =
                IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
            launcher.launch(intent)
        }

        val tabsTitle = listOf("Sign In", "Sign Up")

        val textFieldColors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Black,
            errorIndicatorColor = Primary,
            errorLabelColor = Primary,
            errorCursorColor = Primary
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp), backgroundColor = White
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_large),
                contentDescription = "Large Icon",
                modifier = Modifier.padding(all = 100.dp),
                alignment = Alignment.Center
            )
            Column(verticalArrangement = Arrangement.Bottom) {

                TabRow(selectedTabIndex = tabIndex,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 50.dp),
                    backgroundColor = White,
                    contentColor = Black,
                    indicator = @Composable { tabPositions: List<TabPosition> ->
                        TabRowDefaults.Indicator(
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[tabIndex])
                                .padding(horizontal = 20.dp),
                            color = Primary
                        )
                    }, divider = @Composable {
                        Divider()
                    }) {
                    tabsTitle.forEachIndexed { index: Int, title: String ->
                        Tab(
                            selected = tabIndex == index,
                            onClick = { tabIndex = index },
                            modifier = Modifier.padding(horizontal = 20.dp),
                            text = {
                                Text(text = title)
                            })
                    }
                }
            }

        }
        when (tabIndex) {
            0 -> LoginScreen(
                context,
                navHostController,
                launch = {
                    launch(it)
                },
                viewModel = viewModel,
                textFieldColors = textFieldColors
            )
            1 -> SignUpScreen(
                context,
                navHostController,
                launch = {
                    launch(it)
                },
                viewModel = viewModel,
                textFieldColors = textFieldColors
            )
        }

    }
}