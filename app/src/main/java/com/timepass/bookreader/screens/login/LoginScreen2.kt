package com.timepass.bookreader.screens.login

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.timepass.bookreader.R
import com.timepass.bookreader.navigation.BookReaderScreens
import com.timepass.bookreader.ui.theme.myfonts
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

@SuppressLint("ResourceType")
@Composable
fun LoginScreen2(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val error = remember { mutableStateOf(false) }
    val errorEmptyFields = remember { mutableStateOf(false) }
    val isNetworkAvailable = remember { mutableStateOf(true) }
    val isCheckingConnection = remember { mutableStateOf(true) }
    val context = LocalContext.current
    val connectivityManager = context.getSystemService(ConnectivityManager::class.java)
    val passwordVisibility = remember { mutableStateOf(false) }
    val networkCallback = remember {
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isNetworkAvailable.value = true
            }

            override fun onLost(network: Network) {
                isNetworkAvailable.value = false
            }
        }
    }

    DisposableEffect(Unit) {
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

        onDispose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }
    LaunchedEffect(Unit) {
        checkInternetConnection { isConnected ->
            isNetworkAvailable.value = isConnected
            isCheckingConnection.value = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "বুকস অব বিডি",
                fontSize = 45.sp,
                fontFamily = myfonts,
                modifier = Modifier.padding(top = 50.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            if (isNetworkAvailable.value) {

                OutlinedTextField(
                    value = email.value,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xffff7f28),
                        focusedLabelColor = Color(0xFFFF7421),
                        unfocusedBorderColor = Color(0xFFFF7421)),
                    onValueChange = { email.value = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    trailingIcon = { IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value}) {
                        if (passwordVisibility.value) {
                            Image(painter = painterResource(id = R.drawable.hide), contentDescription ="",
                            modifier = Modifier.size(25.dp))
                        } else {
                            Image(painter = painterResource(id = R.drawable.view) , contentDescription = "",
                                modifier = Modifier.size(25.dp))
                        }

                    }},
                    label = { Text("Password") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFFF7421),
                        focusedLabelColor = Color(0xFFFF7421),
                        unfocusedBorderColor = Color(0xFFFF7421)),
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),

                )

                Spacer(modifier = Modifier.height(16.dp))

                if (error.value) {
                    ErrorMessage(text = "Invalid email or password")
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (errorEmptyFields.value) {
                    EmptyErrorMessage()
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Button(
                    onClick = {

                        if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
                            isValidCredentials(email.value, password.value)
                                .addOnCompleteListener { task: Task<AuthResult> ->
                                    if (task.isSuccessful) {
                                        // Successful login
                                        error.value = false
                                        navController.navigate(BookReaderScreens.HomeScreen.name)
                                    } else {
                                        // Invalid login
                                        error.value = true
                                        val exception = task.exception
                                        if (exception is FirebaseAuthException) {
                                            val errorCode = exception.errorCode
                                            val errorMessage = exception.message
                                        }
                                    }
                                }
                        } else {
                            errorEmptyFields.value = true

                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFFF7421)),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.fillMaxWidth(0.5f)

                )
                {
                    Text(
                        text = "Login",
                        color = Color.White,
                        fontSize = 16.sp
                    )

                }
            }
            else {
                NoInternetContent()
            }
            Row(
                modifier = Modifier.padding(15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
              Text(text = "New User?")
                Text(text = "Create Account", fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.secondaryVariant,
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .clickable {
                                    navController.navigate(BookReaderScreens.UserRegForm.name)
                                })
            }
        }}}


fun isValidCredentials(username: String, password: String): Task<AuthResult> {
    return authenticateUser(username, password)
}
fun authenticateUser(email: String, password: String): Task<AuthResult> {
    val auth = FirebaseAuth.getInstance()
    return auth.signInWithEmailAndPassword(email, password)
}
@Composable
fun ErrorMessage(text: String) {
    Text(
        text = text,
        color = Color.Red,
        fontSize = 12.sp,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun EmptyErrorMessage() {
    Text(text = "Email and Password should not be empty")

}

@Composable
fun NoInternetContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No Internet Connection",
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = { checkInternetConnection{} },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF7421), disabledBackgroundColor = Color(0xFFFF7421).copy(0.5f)),
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(0.5f)
        ) {
            Text(text = "Retry", color = Color.White , fontSize = 20.sp)
        }

        Text(
            text = "Please check your internet connection and try again.",
            style = MaterialTheme.typography.body1,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "You may need to enable Wi-Fi or mobile data.",
            style = MaterialTheme.typography.body1,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}
@OptIn(DelicateCoroutinesApi::class)
 fun checkInternetConnection(callback: (Boolean) -> Unit) {
    GlobalScope.launch(Dispatchers.IO) {
        val socket = Socket()
        try {
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            callback(true)
        } catch (e: IOException) {
            callback(false)
        } finally {
            socket.close()
        }
    }
}





