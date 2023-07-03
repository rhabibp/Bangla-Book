package com.timepass.bookreader.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.timepass.bookreader.extera_component.TypewriterText
import com.timepass.bookreader.navigation.BookReaderScreens
import com.timepass.bookreader.ui.theme.myFont5
import com.timepass.bookreader.ui.theme.myfonts
import kotlinx.coroutines.delay

@Composable
fun BookReaderSplashScreen(navController: NavController,modifier: Modifier = Modifier) {

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
        delay(2000L)
        if (FirebaseAuth.getInstance().currentUser?.email?.isNotEmpty() == true) {
            navController.navigate(BookReaderScreens.HomeScreen.name)
            {
                popUpTo(BookReaderScreens.SplashScreen.name) {
                    inclusive = true
                }
            }
        } else {
            navController.navigate(BookReaderScreens.LoginScreen2.name)
            {
                popUpTo(BookReaderScreens.SplashScreen.name) {
                    inclusive = true
                }
            }

        }

    }

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bangla Reader", fontFamily = myFont5, fontSize = 36.sp)

        Row {

            Text(
                text = "জ্ঞানের পথে",
                modifier = Modifier.padding(start = 160.dp),
                fontSize = 32.sp,
                fontFamily = myfonts,
            )
            TypewriterText(texts = listOf("..."))
        }


    }
}




