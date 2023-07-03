//package com.timepass.bookreader.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.timepass.bookreader.R
import com.timepass.bookreader.components.EmailInput
import com.timepass.bookreader.components.PasswordInput




//@Composable
//fun LoginScreen(navController: NavController, viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
//
//
//    val showLoginForm = rememberSaveable() {
//        mutableStateOf(true)
//    }
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    )
//    {
//        Text(text = "বুকস অব বিডি", fontSize =45.sp , fontFamily = myfonts, modifier = Modifier.padding(top = 50.dp))
//        if (showLoginForm.value) UserForm(
//            loading = false,
//            onCreateAccount = false
//        ) { email, password ->
//            viewModel.userSignIn(email = email, password = password){
//                navController.navigate(BookReaderScreens.HomeScreen.name)
//            }
//        }
//        else {
//            UserForm(loading = false, onCreateAccount = true) { email, password ->
//                viewModel.userAccountCreate(email, password){
//                    navController.navigate(BookReaderScreens.HomeScreen.name)
//                }
//            }
//
//        }
//        Row(
//            modifier = Modifier.padding(15.dp),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            val text = if (showLoginForm.value) "Sign Up" else "Login"
//            val text2 = if (showLoginForm.value) "New User?" else "Already Registered?"
//            Text(text = text2)
//            Text(
//                text,
//                modifier = Modifier
//                    .clickable {
//                        showLoginForm.value = !showLoginForm.value
//                    }
//                    .padding(start = 5.dp),
//                fontWeight = FontWeight.Bold,
//                color = MaterialTheme.colors.secondaryVariant
//            )
//        }
//    }
//
//}


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    loading: Boolean = false,
    onCreateAccount: Boolean = false,
    onDone: (String,String) -> Unit =  { email,password ->},
    navController: NavController

) {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
        , verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (onCreateAccount) Text(
            text = stringResource(id = R.string.name_account),
            modifier = Modifier.padding(start = 5.dp, top = 7.dp, bottom = 5.dp)
        )
        else Text(text = "")
        EmailInput(
            emailState = email,
            enabled = !loading,
            onAction = KeyboardActions { passwordFocusRequest.requestFocus() })
        PasswordInput (Modifier.focusRequester(passwordFocusRequest),
            passwordState = password,
            labelId = "Password",
            enabled = !loading,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onDone(email.value.trim(), password.value.trim())
            }
        )

        SubmitButton(
            textId = if (onCreateAccount) "Create Account" else "Login",
            loading = loading,
            validInput = valid
        ){
            onDone(email.value.trim(),password.value.trim())
            keyboardController?.hide()
        }


    }
}

@Composable
fun SubmitButton(
    textId: String,
    loading: Boolean,
    validInput: Boolean,
    onClick: () -> Unit
) {
    Button(onClick = onClick,
        Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        enabled = !loading && validInput,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF7421), disabledBackgroundColor = Color(0xFFFF7421).copy(0.5f)),

        ) {
        if (loading) CircularProgressIndicator(modifier = Modifier.size(25.dp))
        else Text(text = textId, modifier = Modifier.padding(5.dp), color = Color.White)
    }
}




