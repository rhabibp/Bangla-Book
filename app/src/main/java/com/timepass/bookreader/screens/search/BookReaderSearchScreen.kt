@file:OptIn(ExperimentalComposeUiApi::class)

package com.timepass.bookreader.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.timepass.bookreader.components.InputField
import com.timepass.bookreader.components.downloader.DownloadField
import com.timepass.bookreader.model.MyBooks
import com.timepass.bookreader.navigation.BookReaderScreens
import com.timepass.bookreader.ui.theme.myFont5
import java.util.Date

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController) {
    Scaffold(topBar = {
        Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
            imageVector = Icons.Default.ArrowBack,
            modifier = Modifier
                .padding(start = 15.dp, top = 15.dp, end = 60.dp)
                .clickable { navController.navigate(BookReaderScreens.HomeScreen.name) },
            contentDescription = "Back Arrow", tint = Color(0xFFFF7421)
        )
        Text(text = "Search Books",
            color = Color(0xFFFF7421),
            fontSize = 25.sp,
            fontFamily = myFont5,
            modifier = Modifier.padding(top = 10.dp))

        }

    })
    {
        DownloadField(context = LocalContext.current)


        }

    }




@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    hint: String = "Search",
    onSearch: (String)-> Unit={},
    
) {
    Column() {
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState) {
            searchQueryState.value.trim().isNotEmpty()

        }
        InputField(
            valueState = searchQueryState,
            labelId = "Book's Name",
            enabled = true,
            onAction = KeyboardActions(){
                if (!valid)return@KeyboardActions
                keyboardController?.hide()
            }
        )

        
    }


}

@Composable
fun SearchedBookList(navController: NavController) {

    val listofBooks = listOf(MyBooks(timeStamp = Date())
    )
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(items = listofBooks) { book ->
            BookRow(book, navController)
        }

    }

}


@Composable
fun BookRow(book: MyBooks, navController: NavController) {
    Card(modifier = Modifier
        .clickable { }
        .fillMaxWidth()
        .height(150.dp)
        .padding(5.dp), shape = RectangleShape, elevation = 4.dp) {
        Row(modifier = Modifier, verticalAlignment = Alignment.Top){
            val imageUrl = "https://images2.imgbox.com/6e/38/nUU8ctyh_o.jpg"
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Books Image",
                modifier = Modifier
                    .size(150.dp)
                    .padding(3.dp)
            )
            Column() {
                Text(text = book.title.toString(), overflow = TextOverflow.Ellipsis)
                Text(text = "Author: ${book.author}", overflow = TextOverflow.Clip, style = MaterialTheme.typography.caption)


            }

        }

    }

}
















