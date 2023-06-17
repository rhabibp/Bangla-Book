package com.timepass.bookreader.screens.home

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.timepass.bookreader.components.BookReaderAppBar
import com.timepass.bookreader.components.FABContent
import com.timepass.bookreader.components.HomeContent
import com.timepass.bookreader.components.ListCardArea
import com.timepass.bookreader.model.MyBooks
import com.timepass.bookreader.navigation.BookReaderScreens
import com.timepass.bookreader.screens.detail.DetailsViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, detailsViewModel: DetailsViewModel) {
    Scaffold(topBar = {
        BookReaderAppBar(
            title = "Bangla Reader",
            navController = navController
        )
    }, floatingActionButton = {
        FABContent {
            navController.navigate(BookReaderScreens.SearchScreen.name)
        }


    })
    {
        Surface {
            HomeContent(navController, context = LocalContext.current)



        }

//        BookListView(navController)
    }

}

    @Composable
    fun ReadingRightNowArea(books: List<MyBooks>, navController: NavController) {
        ListCardArea()
    }







