package com.nativemobilebits.loginflow.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nativemobilebits.loginflow.data.login.LoginViewModel
import com.nativemobilebits.loginflow.R
import com.nativemobilebits.loginflow.components.*
import com.nativemobilebits.loginflow.data.login.LoginUIEvent
import com.nativemobilebits.loginflow.navigation.PostOfficeAppRouter
import com.nativemobilebits.loginflow.navigation.Screen
import com.nativemobilebits.loginflow.navigation.SystemBackButtonHandler

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                NormalTextComponent(value = stringResource(id = R.string.login))
                HeadingTextComponent(value = stringResource(id = R.string.welcome))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.message),
                    onTextChanged = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))
                UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                       loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
                })
            }
        }

        if(loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }
    }


    SystemBackButtonHandler {
        PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}