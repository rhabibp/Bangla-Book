package com.timepass.bookreader.screens.detail

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.timepass.bookreader.components.downloader.AndroidDownloader

@Composable
fun DetailsScreen(
    navController: NavController,
    title: String,
    author: String,
    bookDescription: String,
//        author: String,
    bookDownloadLink: Uri,
//        bookDescription: String
) {
//        Scaffold(topBar = {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                        Icon(
//                                imageVector = Icons.Default.ArrowBack,
//                                modifier = Modifier
//                                        .padding(start = 15.dp, top = 15.dp, end = 60.dp)
//                                        .clickable { navController.navigate(BookReaderScreens.HomeScreen.name) },
//                                contentDescription = "Back Arrow", tint = Color(0xFFFF7421)
//                        )
//                        Text(
//                                text = "Book's Detail",
//                                color = Color(0xFFFF7421),
//                                fontSize = 25.sp,
//                                fontFamily = myFont5,
//                                modifier = Modifier.padding(top = 10.dp)
//                        )
//
//                }
//
//        }) {
                Column() {
                        val context = LocalContext.current
                        Text(text = "Details: $title", fontSize = 40.sp)
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = "Details: $author", fontSize = 40.sp)
                        Text(text = "Details: $bookDescription", fontSize = 40.sp)
                        Button(onClick = {
                                AndroidDownloader(context).downloadFile(bookDownloadLink.toString())

                        }) {
                                Text(text = "Download")

                        }

                }

        }



