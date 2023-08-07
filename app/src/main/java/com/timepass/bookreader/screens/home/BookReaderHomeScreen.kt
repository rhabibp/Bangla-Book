package com.timepass.bookreader.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavController
import com.timepass.bookreader.components.BookReaderAppBar
import com.timepass.bookreader.components.FABContent
import com.timepass.bookreader.components.HomeContent
import com.timepass.bookreader.components.ListCardArea
import com.timepass.bookreader.model.MyBooks
import com.timepass.bookreader.navigation.BookReaderScreens
import com.timepass.bookreader.navigationDrawer.DrawerBody
import com.timepass.bookreader.navigationDrawer.DrawerHeader
import com.timepass.bookreader.navigationDrawer.ManuItem
import com.timepass.bookreader.screens.detail.DetailsViewModel
import kotlinx.coroutines.launch
import java.util.Date

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, detailsViewModel: DetailsViewModel) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
        BookReaderAppBar(
            title = "Bangla Reader",
            navController = navController,
            onNavigationClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        )
    },
        drawerContent = {


            Column(Modifier.fillMaxWidth()){
            DrawerHeader()
            DrawerBody(items = listOf(
                ManuItem(
                    title = "Home",
                    icon = Icons.Default.Home
                ),
                ManuItem(
                    title = "Categories",
                    icon = Icons.Default.Menu
                )
            ), modifier = Modifier, onClick = {
                when(it.title) {
                    "Home" -> navController.navigate(BookReaderScreens.HomeScreen.name)
                    "Categories" -> navController.navigate(BookReaderScreens.Categories.name)
                }
            })

        }


        }
        ,
        floatingActionButton = {
        FABContent {
            navController.navigate(BookReaderScreens.SearchScreen.name)
        }


    }, drawerShape = customShape()
    )
    {

            HomeContent(navController, context = LocalContext.current)






//        BookListView(navController)
    }

}

fun customShape() = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(rect = Rect(left = 0f,0f,600f,3000f) )
    }

}

@Composable
    fun ReadingRightNowArea(books: List<MyBooks>, navController: NavController) {
        ListCardArea(book = MyBooks(timeStamp = Date()))
    }







