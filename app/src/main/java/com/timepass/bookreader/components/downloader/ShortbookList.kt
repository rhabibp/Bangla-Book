package com.timepass.bookreader.components.downloader

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.Query
import com.jet.firestore.JetFirestore
import com.jet.firestore.getListOfObjects
import com.timepass.bookreader.components.BookRating
import com.timepass.bookreader.model.MyBooks
import com.timepass.bookreader.navigation.BookReaderScreens

@Composable
fun NewBookList(navController: NavController) {
    var bookList by remember { mutableStateOf<List<MyBooks>?>(null) }
    val itemsToLoad = remember { mutableStateOf(5) }

    JetFirestore(path = { collection("bookDetails") },
        queryOnCollection = { orderBy("timeStamp", Query.Direction.DESCENDING) },
//        limitOnSingleTimeCollectionFetch = 2,
        onSingleTimeCollectionFetch = { value, exception ->
            bookList = value.getListOfObjects()

        }
    ) {
        bookList?.let {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(it.subList(0, itemsToLoad.value)) {
                        Extracted(
                            it,
                            onItemClick = {
                                navController.navigate(
                                    BookReaderScreens.DetailsScreen.name +
                                            "/${it.title}/${it.author}/${it.bookDescription}/${
                                                Uri.encode(
                                                    it.bookDownloadLink
                                                )
                                            }}"
                                )
                            }
                        )

                    }

                    item {
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                            Button(
                                modifier = Modifier.height(50.dp).width(90.dp), onClick = {
                                    val nextIndex = itemsToLoad.value + 5
                                    if (nextIndex <= bookList!!.size) {
                                        itemsToLoad.value = nextIndex
                                    } else if (itemsToLoad.value < bookList!!.size) {
                                        itemsToLoad.value = bookList!!.size
                                    }
                                },
                                enabled = itemsToLoad.value < bookList!!.size,
                                colors = ButtonDefaults.buttonColors(Color(0xffFF7421))
                            )
                            {
                                Text(text = "Next", color = Color.White)
                            }
                        }
                    }
                }


            }

        }


    }
}

@Composable
fun Extracted(it: MyBooks,onItemClick: ()-> Unit) {
    Card(modifier = Modifier
        .clickable { onItemClick() }
        .fillMaxWidth(), elevation = 5.dp) {

    Column(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .padding(top = 5.dp)
                .clip(RoundedCornerShape(10.dp)),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(it.bookImageLink.toString()),
                contentScale = ContentScale.Fit,
                alignment = Alignment.CenterStart,
                alpha = 0.9f,
                contentDescription = "book image",
                modifier = Modifier
                    .width(130.dp)
                    .height(100.dp)
                    .padding(5.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier
                    .padding(top = 0.dp), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    modifier = Modifier.padding(2.dp)
                )

                BookRating(3.5)

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
                text = it.title.toString(),
                style = MaterialTheme.typography.caption
            )
        }

       }
    }
    Spacer(modifier = Modifier
        .height(3.dp)
        .padding(start = 5.dp)
        .fillMaxWidth(0.5f)
        .background(Color(0xffFF7421)))

}



