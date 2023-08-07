package com.timepass.bookreader.components.downloader

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.Query
import com.jet.firestore.JetFirestore
import com.jet.firestore.getListOfObjects
import com.timepass.bookreader.model.MyBooks
import com.timepass.bookreader.navigation.BookReaderScreens
import com.timepass.bookreader.screens.login.NoInternetContent
import com.timepass.bookreader.screens.login.checkInternetConnection

@Composable
fun NewBookList(navController: NavController) {
    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }
    val isConnected = remember { mutableStateOf(true) }
    val connectivityManager =
        context.getSystemService(ConnectivityManager::class.java)
    val isNetworkAvailable = remember { mutableStateOf(true) }
    val isCheckingConnection = remember { mutableStateOf(true) }
    var bookList by remember { mutableStateOf<List<MyBooks>?>(null) }
    val itemsToLoad = remember { mutableStateOf(6) }
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


    if (isNetworkAvailable.value) {
        JetFirestore(path = { collection("bookDetails") },
            queryOnCollection = { orderBy("timeStamp", Query.Direction.DESCENDING) },
//        limitOnSingleTimeCollectionFetch = 2,
            onSingleTimeCollectionFetch = { value, exception ->
                bookList = value.getListOfObjects()

            }
        ) {
            bookList?.let {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize(0.935f)
                    ) {
                            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                                items(it.subList(0, itemsToLoad.value)) {
                                    Extracted(it, onItemClick = {
                                        navController.navigate(
                                            BookReaderScreens.DetailsScreen.name +
                                                    "/${it.title}/${it.author}/${it.bookDescription}/${
                                                        Uri.encode(
                                                            it.bookDownloadLink
                                                        )
                                                    }/${Uri.encode(it.bookImageLink)}"
                                        )
                                    })

                                }

                            }


                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Box(contentAlignment = Alignment.CenterEnd) {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth(0.2f), onClick = {
                                val nextIndex = itemsToLoad.value + 5
                                if (nextIndex <= bookList!!.size) {
                                    itemsToLoad.value = nextIndex
                                } else if (itemsToLoad.value < bookList!!.size) {
                                    itemsToLoad.value = bookList!!.size
                                }
                            }
                                ,
                            colors = ButtonDefaults.buttonColors(
                                Color(0xFFFF7421),
                                disabledBackgroundColor = Color(0x00FF2121)
                            )
                        )
                        {
                            Text(text = "Next →", color = Color.White, fontSize = 15.sp)
                        }
                    }
                }


            }

        }   else {
        NoInternetContent()
    }

    }









@Composable
fun Extracted(it: MyBooks,onItemClick: ()-> Unit) {
    Card(modifier = Modifier
        .clickable { onItemClick() }
        .padding(5.dp)
, elevation = 3.dp) {

    Column(
        modifier = Modifier, verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(top = 5.dp)
                .clip(RoundedCornerShape(10.dp)),
//            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(it.bookImageLink.toString()),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
//                alpha = 2f,
                contentDescription = "book image",
                modifier = Modifier
                    .width(150.dp)
                    .height(160.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.width(10.dp))


            Column(
                modifier = Modifier
                    .padding(top = 0.dp),
                horizontalAlignment = Alignment.End,
//                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .padding(2.dp)
                        .clickable { }
                )


            }
        }


        Spacer(modifier = Modifier.height(10.dp))
        Column(Modifier.padding(start = 5.dp)) {

            Text(
                text = it.title.toString(),
                Modifier,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = it.author.toString(),
                style = MaterialTheme.typography.caption
            )
        }

       }
    }
    Spacer(modifier = Modifier
        .height(2.dp)
        .padding(start = 5.dp, end = 5.dp)
        .fillMaxWidth(0.5f)
        .background(Color(0xffFF7421)))


}



