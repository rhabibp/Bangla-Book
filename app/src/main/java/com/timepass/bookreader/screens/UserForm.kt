package com.timepass.bookreader.screens

import SubmitButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.timepass.bookreader.R
import com.timepass.bookreader.components.EmailInput
import com.timepass.bookreader.components.PasswordInput
import com.timepass.bookreader.navigation.BookReaderScreens
import com.timepass.bookreader.screens.login.LoginScreenViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserRegForm(    loading: Boolean = false,
                    onCreateAccount: Boolean = false,
                    onDone: (String,String) -> Unit =  { email,password ->},
                    navController: NavController,
                    viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
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
        Text(
            text = stringResource(id = R.string.name_account),
            modifier = Modifier.padding(start = 5.dp, top = 7.dp, bottom = 5.dp)
        )
        EmailInput(
            emailState = email,
            onAction = KeyboardActions { passwordFocusRequest.requestFocus() })
        PasswordInput (
            Modifier.focusRequester(passwordFocusRequest),
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
            textId = ("Create Account"),
            loading = loading,
            validInput = valid
        ){
            onDone(email.value.trim(),password.value.trim())
            viewModel.userAccountCreate(email.value, password.value){
                    navController.navigate(BookReaderScreens.HomeScreen.name)
                }
            keyboardController?.hide()
        }


    }

}