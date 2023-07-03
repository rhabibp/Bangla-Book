package com.timepass.bookreader.screens.detail

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.timepass.bookreader.components.downloader.AndroidDownloader
import com.timepass.bookreader.navigation.BookReaderScreens
import com.timepass.bookreader.ui.theme.myFont5

@SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    navController: NavController,
    title: String? = null,
    author: String? = null,
    bookDescription: String? = null,
    bookDownloadLink: Uri,
    bookImageLink: Uri
) {
    Scaffold(topBar = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, end = 60.dp)
                    .clickable { navController.navigate(BookReaderScreens.HomeScreen.name) },
                contentDescription = "Back Arrow", tint = Color(0xFFFF7421)
            )
            Text(
                text = "Book's Detail",
                color = Color(0xFFFF7421),
                fontSize = 25.sp,
                fontFamily = myFont5,
                modifier = Modifier.padding(top = 10.dp)
            )

        }

    }) {

        BookDetails(bookImageLink, title, author, bookDescription, bookDownloadLink)
    }

        }


@Composable
private fun BookDetails(
    bookImageLink: Uri,
    title: String?,
    author: String?,
    bookDescription: String?,
    bookDownloadLink: Uri
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 15.dp)
    ) {
        Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {


            Image(
                painter = rememberAsyncImagePainter(bookImageLink.toString()),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "book image",
                modifier = Modifier
                    .width(220.dp)
                    .height(230.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
        Column(Modifier.padding(top = 5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val context = LocalContext.current
            Text(
                text = "Name: $title",
                fontSize = 20.sp,
                fontStyle = MaterialTheme.typography.caption.fontStyle
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Author: $author",
                fontSize = 15.sp,
                fontStyle = MaterialTheme.typography.subtitle1.fontStyle
            )
            Text(text = "Book Details: \n $bookDescription", fontSize = 15.sp,
                modifier = Modifier.padding(5.dp))
            Button(onClick = {

                AndroidDownloader(context).downloadFile(bookDownloadLink.toString())
            },
                colors = ButtonDefaults.buttonColors(Color(0xFFFF7421))
            ) {
                Text(text = "Download PDF", color = Color.White)

            }
        }
    }
}







